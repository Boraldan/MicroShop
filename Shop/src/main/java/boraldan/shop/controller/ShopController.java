package boraldan.shop.controller;


import boraldan.entitymicro.bank.entity.BankAccount;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.dto.ListItemDtoBuilder;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.shop.entity.item.transport.car.Types;
import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.dto.StorageBuilder;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.entitymicro.test.Fly;
import boraldan.entitymicro.test.Lot;
import boraldan.shop.controller.feign.BankFeign;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.mq.bank.MqShopService;
import boraldan.shop.repository.FlyRepo;
import boraldan.shop.service.builder.PriceBuilder;
import boraldan.shop.service.i_service.CategoryService;
import boraldan.shop.service.provider.ItemServiceClassProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ShopController {
    public static int counter;

    private final CategoryService categoryService;
    private final ItemServiceClassProvider itemService;
    private final FlyRepo flyRepo;
    private final ModelMapper modelMapper;
    private final StorageFeign storageFeign;
    private final BankFeign bankFeign;
    private final MqShopService mqShopService;
    private final RedisTemplate<String, Object> redis;

    @PostMapping("/category")
    public ResponseEntity<?> category(@RequestBody Category category) {
        System.out.println("1 - " +  category);

//        List<Item> itemList = categoryService.findByCategoryName(CategoryName.CAR).get().getItems();

        List<Car> itemList = categoryService.findByCategoryName(CategoryName.CAR).get().getItems().stream()
                .map(el -> (Car) el).toList();

//        ListItemDto listItemDto = new  ListItemDtoBuilder().setItemList(itemList).build();


        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/item")
    public ResponseEntity<Item> item() {
        Item item = itemService.getService(Item.class).getById(1L);
        item.setStorage(storageFeign.getQuantity(new StorageBuilder().setId(item.getStorageId()).setStorageClazz(item.getStorageClazz()).build()).getBody());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/addcar")
    public ResponseEntity<?> addItem() {
        Car car = new Car();
        car.setName("Vesta_%s".formatted(++counter));
        car.setFactory("Lada_%s".formatted(++counter));
        car.setYear(2010);
        car.setTypes(Types.SEDAN);
        car.setFuel(Fuel.GASOLINE);

//        CarPrice carPrice = new PriceBuilder(CarPrice.class).setBasePrice(2000).setCoefficient(1.5).builder();
        car.setPrice(new PriceBuilder(car.getPriceClazz()).setBasePrice(2000).setCoefficient(1.5).builder());

        Storage storage = new CarStorage();
        storage.setQuantity(3);
        storage.setReserve(3);
        storage = storageFeign.saveItem(storage).getBody();
        car.setStorageId(storage != null ? storage.getId() : null);


        Car car2 = convertToNeedItem(itemService.getService(car.getItemClazz()).save(car), car.getItemClazz());
//        Item item =  itemService.getService(car.getItemClazz()).save(car);


        System.out.println("Запрос Car1 --> 1 " + car2);
        return new ResponseEntity<>(car2, HttpStatus.OK);
    }

    @GetMapping("/addbike")
    public ResponseEntity<?> addBike() {

        Bike bike = new Bike();
        bike.setName("bike_%s".formatted(++counter));
        bike.setFactory("bike_%s".formatted(++counter));
        bike.setYear(2010);
        bike.setFuel(Fuel.GASOLINE);

//        BikePrice bikePrice = new PriceBuilder(bike.getPriceClazz()).setBasePrice(1000).setCoefficient(1.2).builder();
        bike.setPrice(new PriceBuilder(bike.getPriceClazz()).setBasePrice(1000).setCoefficient(1.2).builder());

        Storage storage = new BikeStorage();
        storage.setQuantity(5);
        storage.setReserve(5);
        storage = storageFeign.saveItem(storage).getBody();
        bike.setStorageId(storage != null ? storage.getId() : null);

        Item item2 = itemService.getService(bike.getItemClazz()).save(bike);
        System.out.println("Запрос bike --> 1 " + item2);
        return new ResponseEntity<>(item2, HttpStatus.OK);
    }

//    @SneakyThrows
//    @GetMapping("/addcar")
//    public ResponseEntity<Car> addCar() {
//
//        Car car = new Car();
//        car.setName("Vesta_%s".formatted(++counter));
//        car.setFactory("Lada_%s".formatted(++counter));
//        car.setYear(2010);
//        car.setTypes(Types.SEDAN);
//        car.setFuel(Fuel.GASOLINE);
//        car.setCategory(categoryRepo.findByCategoryName(CategoryName.CAR).get());
//
//        CarPrice carPrice = new PriceBuilder<CarPrice>().setBasePrice(2000).setCoefficient(1.5).builder();
//        car.setPrice(carPrice);
//
//        StorageDto storageDto = new StorageDto();
//        storageDto.setQuantity(3);
//        storageDto.setReserve(3);
//        storageDto.setStorageClazz(CarStorage.class);
//        Storage storage = storageFeign.saveItem(storageDto).getBody();
//        car.setStorageId(storage != null ? storage.getId() : null);
//
//
////        ItemService<Car, ItemUnifiedRepo<Car>> carService = (ItemService<Car, ItemUnifiedRepo<Car>>) itemServiceMap.get(Car.class);
////        car = carService.save(car);
////        car =  itemServiceProvider.getMapBeanItem().get(Car.class).save(car);
//
//        System.out.println("Запрос Car1 --> 1 " + car);
//        return new ResponseEntity<>(car, HttpStatus.OK);
//    }

    @GetMapping("/dellcar")
    public ResponseEntity<?> dellCar() {

//        carService.dellById(4L);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/session")
    public ResponseEntity<String> getSessionId(@RequestHeader HttpHeaders headers) {

//        session.setAttribute(session.getId(), "Добавили в Redis");
//        session.setAttribute("s1", "Добавили в сессию атрибут s1");

//        redisService.getListOps().leftPush("s2", "Redis будешь работать?");
//        String getRedis = (String) redisTemplate.opsForList().rightPop("s1");
//        System.out.println("redis --> " + redisTemplate.opsForList().rightPop("s1"));
//        System.out.println("redis --> " + redisTemplate.opsForList().rightPop("s2"));

        if (headers.containsKey("IdSession")) {
            String idSession = headers.getFirst("IdSession");

            System.out.println(redis.opsForValue().get(idSession));
        }


//        String getSessionId="";
//
//        // Получаем все ключи
//        Set<String> keys = redis.keys("*");
//
//        // Выводим все ключи
//        System.out.println("All keys in Redis:");
//        for (String key : keys) {
//            System.out.println(key);
//            System.out.println(redis.opsForValue().get(key));
//            getSessionId = key;
//        }

        return new ResponseEntity<>("1", HttpStatus.OK);
    }


    @GetMapping("/card")
    public ResponseEntity<String> getByCard() {
        mqShopService.sendMessage(11111L);
        return new ResponseEntity<>("Send. Waiting...", HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<ListItemDto> items() {
        List<Item> itemList = itemService.getService(Item.class).getAll();
        return new ResponseEntity<>(new ListItemDtoBuilder().setItemList(itemList).build(), HttpStatus.OK);
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

    @GetMapping("/fly")
    public ResponseEntity<Fly> getFly() {
        Fly fly = flyRepo.findById(1L).get();
        log.info("Запрос Fly --> " + fly);
        return new ResponseEntity<>(fly, HttpStatus.OK);
    }


    //region методы ModelMapper
    // метод конвертации из Item в любой клас наследника
    public <T extends Item> T convertToNeedItem(Item item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(item, targetType);
    }


    // добавляем Storage в Item
    private ListItemDto mapToItemList(List<Item> itemList, ListStorageDto listStorageDto) {
        Map<UUID, Storage> storageMap = new HashMap<>();
        for (Storage storage : listStorageDto.getStorageList()) {
            storageMap.put(storage.getId(), storage);
        }
        List<Item> newItemList = itemList.stream().map(el -> {
            el.setStorage(storageMap.get(el.getStorageId()));
            return el;
        }).toList();
        return new ListItemDtoBuilder().setItemList(newItemList).build();
    }

//    private CarDto convertToCarDTO(Car car) {
//        return modelMapper.map(car, CarDto.class);
//    }

//    вариант добавить свой маппинг отдельных полей в классе
//    modelMapper.createTypeMap(Car.class, CarDTO.class)
//            .addMapping(src -> src.getPrice().getCustomPrice(), CarDTO::setPrice);

    //endregion


}

