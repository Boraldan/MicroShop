package boraldan.shop.controller;


import boraldan.entitymicro.bank.entity.BankAccount;
import boraldan.entitymicro.shop.dto.ItemListDto;
import boraldan.entitymicro.shop.dto.SpecificationDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.shop.entity.item.transport.car.Types;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.specification.SpecItem;
import boraldan.entitymicro.storage.dto.StorageListDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.entitymicro.test.Lot;
import boraldan.entitymicro.toolbox.builder.*;
import boraldan.shop.controller.feign.BankFeign;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.mq.account.OutputMqFrontService;
import boraldan.shop.mq.bank.MqShopService;
import boraldan.shop.redis.RedisService;
import boraldan.shop.service.i_service.CategoryService;
import boraldan.shop.service.provider.ItemServiceClassProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ShopController {
    public static int counter;
    private final CategoryService categoryService;
    private final ItemServiceClassProvider itemService;
    private final ModelMapper modelMapper;
    private final StorageFeign storageFeign;
    private final BankFeign bankFeign;
    private final MqShopService mqShopService;
    private final RedisService redisService;

    private final OutputMqFrontService outputMqFrontService;


    @PutMapping("/mq")
    public ResponseEntity<Void> mqTest(@RequestHeader("Authorization") String token) {

        outputMqFrontService.sendMessage(token);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/uuidlist")
    public ResponseEntity<List<Item>> getByUuidList(@RequestBody List<UUID> uuidList) {
        return ResponseEntity.ok(itemService.getService(Item.class).getByUuidList(uuidList));
    }

    @PostMapping("/category")
    public ResponseEntity<List<Item>> getAllByCategory(@RequestBody Category category,
                                                       Principal principal
            ,                                                      @RequestHeader("REDIS") String redisKey

    ) {



        mqShopService.sendMessage(1010101010101L);


        System.out.println("category    1 -->  " + category);
        System.out.println("principal   2 -->  " + principal);
        System.out.println("cart    3 -->  " + redisService.getCart(redisKey));

        List<Item> itemList;
        if (category.getCategoryName().equals(CategoryName.ITEM)) {
            itemList = itemService.getService(Item.class).getAll();
        } else {
            itemList = categoryService.getListByCategoryName(category.getCategoryName());
        }
        return ResponseEntity.ok(itemList);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> itemList = itemService.getService(Item.class).getAll();
        return ResponseEntity.ok(itemList);
    }

    /**
     * Вариант поиска используя interface Specification<T>
     *
     * @param spec Specification<T>
     * @return Page<T>
     */
    @PostMapping("/items/spec")
    public ResponseEntity<Page<Item>> getAllBySpecification(@RequestBody SpecificationDto spec) {
        // TODO: 06.05.2024 добавить валидацию SpecificationDto
        Class<? extends Item> clazz;
        if (spec.getCategoryName().equals(CategoryName.ITEM)) {
            clazz = Item.class;
        } else {
            Optional<Item> itemOptional = itemService.getService(Item.class).findFirstByCategoryName(spec.getCategoryName());
            if (itemOptional.isPresent()) {
                clazz = itemOptional.get().getItemClazz();
            } else {
                return ResponseEntity.ok(new PageImpl<>(Collections.emptyList(),
                        PageRequest.of(spec.getPage(), spec.getPageSize()), 0));
            }
        }
        return ResponseEntity.ok(itemService.getService(clazz).
                getAllBySpecification(convertToSpecItem(spec), convertToPageRequest(spec)));
    }

    /**
     * Вариант поиска @Query запроса по параметрам
     *
     * @param spec содержит параметры
     * @return Page<T>
     */
    @PostMapping("/items/param")
    public ResponseEntity<Page<Item>> getAllByParam(@RequestBody SpecificationDto spec) {
        // TODO: 06.05.2024 добавить валидацию SpecificationDto
        Class<? extends Item> clazz;
        if (spec.getCategoryName().equals(CategoryName.ITEM)) {
            clazz = Item.class;
        } else {
            Optional<Item> itemOptional = itemService.getService(Item.class).findFirstByCategoryName(spec.getCategoryName());
            if (itemOptional.isPresent()) {
                clazz = itemOptional.get().getItemClazz();
            } else {
                return ResponseEntity.ok(new PageImpl<>(Collections.emptyList(),
                        PageRequest.of(spec.getPage(), spec.getPageSize()), 0));
            }
        }
        return ResponseEntity.ok(itemService.getService(clazz).
                getAllByParam(convertToSpecItem(spec), convertToPageRequest(spec)));
    }

    @PostMapping("/item")
    public ResponseEntity<?> item(@RequestBody UUID itemId) {
        Optional<Item> item = itemService.getService(Item.class).getById(itemId);
        return item.<ResponseEntity<?>>map(value -> ResponseEntity.ok(convertToNeedItem(value, value.getItemClazz())))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/addcar")
    public ResponseEntity<?> addItem() {
        Car car = CarBuilder.create()
                .setName("Vesta_%s".formatted(++counter))
                .setFactory("Vesta_%s".formatted(++counter))
                .setYear(2010)
                .setTypes(Types.SEDAN)
                .setFuel(Fuel.GASOLINE)
                .setPrice(PriceBuilder.creat(CarPrice.class).setBasePrice(2000).setCoefficient(1.5).build())
                .build();

        Item item = itemService.getService(car.getItemClazz()).save(car);

        item.setStorage(storageFeign.saveStorage(StorageBuilder.creat(CarStorage.class)
                        .setItemId(item.getId())
                        .setQuantity(3)
                        .setReserve(3)
                        .build())
                .getBody());

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/addbike")
    public ResponseEntity<?> addBike() {
        Bike bike = BikeBuilder.create()
                .setName("bike_%s".formatted(++counter))
                .setFactory("bike_%s".formatted(++counter))
                .setWheels(2)
                .setYear(2010)
                .setFuel(Fuel.ELECTRIC)
                .setPrice(PriceBuilder.creat(BikePrice.class).setBasePrice(1000).setCoefficient(1.2).build())
                .build();

        Item itemBike = itemService.getService(bike.getItemClazz()).save(bike);

        itemBike.setStorage(storageFeign.saveStorage(StorageBuilder.creat(BikeStorage.class)
                        .setItemId(itemBike.getId())
                        .setQuantity(5)
                        .setReserve(5)
                        .build())
                .getBody());

        return new ResponseEntity<>(itemBike, HttpStatus.OK);
    }

    @DeleteMapping("/item/delete{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") UUID itemId) {
        System.out.println(itemId);
        // TODO: 03.05.2024 добавить в сервис метод удаления из Storage
        itemService.getService(Item.class).deleteById(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/card")
    public ResponseEntity<String> getByCard() {
        mqShopService.sendMessage(11111L);
        return new ResponseEntity<>("Send. Waiting...", HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllAccounts() {
        return bankFeign.getAllAccounts();
    }

    @GetMapping
    public ResponseEntity<Lot> start() {
        mqShopService.sendMessage(11111L);
        Lot lot = new Lot();
        lot.setLot(15);
        return new ResponseEntity<>(lot, HttpStatus.OK);
    }

    //region методы ModelMapper
    // метод конвертации из Item в любой клас наследника
    public <T extends Item> T convertToNeedItem(Item item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(item, targetType);
    }

    // добавляем Storage в Item
    private ItemListDto mapToItemList(List<Item> itemList, StorageListDto storageListDto) {
        Map<UUID, Storage> storageMap = new HashMap<>();
        for (Storage storage : storageListDto.getStorageList()) {
            storageMap.put(storage.getItemId(), storage);
        }
        List<Item> newItemList = itemList.stream().map(i -> {
            i.setStorage(storageMap.get(i.getId()));
            return i;
        }).toList();
        return new ItemDtoListBuilder().setItemList(newItemList).build();
    }

    private SpecItem convertToSpecItem(SpecificationDto specDto) {
        return modelMapper.map(specDto, SpecItem.class);
    }

    private Pageable convertToPageRequest(SpecificationDto specDto) {
        //    Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        if (specDto.getSortByPrice() != null) {
            return PageRequest.of(specDto.getPage(), specDto.getPageSize(), Sort.by(specDto.getSortByPrice()));
        }
        return PageRequest.of(specDto.getPage(), specDto.getPageSize());
    }

//    вариант добавить свой маппинг отдельных полей в классе
//    modelMapper.createTypeMap(Car.class, CarDTO.class)
//            .addMapping(src -> src.getPrice().getCustomPrice(), CarDTO::setPrice);

    //endregion

}

