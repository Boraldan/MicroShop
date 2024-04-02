package boraldan.shop.controller;


import boraldan.entitymicro.bank.entity.BankAccount;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.dto.ListItemDtoBuilder;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.shop.entity.item.transport.car.Types;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.entitymicro.storage.entity.dto.StorageDtoBuilder;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.entitymicro.test.Fly;
import boraldan.entitymicro.test.Lot;
import boraldan.shop.controller.feign.BankFeign;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.mq.bank.MqShopService;
import boraldan.shop.repository.CategoryRepo;
import boraldan.shop.repository.FlyRepo;
import boraldan.shop.repository.ItemRepo;
import boraldan.shop.service.BikePriceServiceV1;
import boraldan.shop.service.BikeServiceV1;
import boraldan.shop.service.CarPriceServiceV1;
import boraldan.shop.service.CarService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
@AllArgsConstructor
public class ShopController {

    //    private final ServiceApi serviceApi;
    private final HttpSession session;
    private final CarService carService;
    private final CategoryRepo categoryRepo;
    private final FlyRepo flyRepo;
    private final ModelMapper modelMapper;
    private final ItemRepo itemRepo;
    private final StorageFeign storageFeign;
    private final BankFeign bankFeign;
    private final MqShopService mqShopService;
    private final RedisTemplate<String, Object> redis;
    private final CarPriceServiceV1 carPriceService;
    private final BikePriceServiceV1 bikePriceService;
    private final BikeServiceV1 bikeService;

    public static int counter;

    @GetMapping("/item")
    public ResponseEntity<Item> item() {
        Item item = itemRepo.findById(1L).orElse(null);
        item.setStorage(storageFeign.getQuantity(new StorageDtoBuilder().setId(item.getStorageId()).setStorageClazz(item.getStorageClazz()).build()).getBody());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/addbike")
    public ResponseEntity<?> addBike() {

        Bike bike = new Bike();
        bike.setName("bike_%s".formatted(++counter));
        bike.setFactory("bike_%s".formatted(++counter));
        bike.setYear(2010);
        bike.setFuel(Fuel.GASOLINE);
        bike.setCategory(categoryRepo.findByCategoryName(CategoryName.BIKE).get());

        BikePrice bikePrice = bikePriceService.getPriceBuilder().setBasePrice(1000).setCoefficient(1.2).builder();
        bike.setPrice(bikePrice);

        StorageDto storageDto = new StorageDto();
        storageDto.setQuantity(5);
        storageDto.setReserve(5);
        storageDto.setStorageClazz(BikeStorage.class);
        Storage storage = storageFeign.saveItem(storageDto).getBody();
        bike.setStorageId(storage != null ? storage.getId() : null);

        bike = bikeService.save(bike);

        System.out.println("Запрос bike --> 1 " + bike);
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/addcar")
    public ResponseEntity<Car> addCar() {

        Car car = new Car();
        car.setName("Vesta_%s".formatted(++counter));
        car.setFactory("Lada_%s".formatted(++counter));
        car.setYear(2010);
        car.setTypes(Types.SEDAN);
        car.setFuel(Fuel.GASOLINE);
        car.setCategory(categoryRepo.findByCategoryName(CategoryName.CAR).get());

        CarPrice carPrice = carPriceService.getPriceBuilder().setBasePrice(2000).setCoefficient(1.5).builder();
        car.setPrice(carPrice);

        StorageDto storageDto = new StorageDto();
        storageDto.setQuantity(3);
        storageDto.setReserve(3);
        storageDto.setStorageClazz(CarStorage.class);
        Storage storage = storageFeign.saveItem(storageDto).getBody();
        car.setStorageId(storage != null ? storage.getId() : null);
        car = carService.save(car);

        System.out.println("Запрос Car1 --> 1 " + car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/dellcar")
    public ResponseEntity<?> dellCar() {

        carService.dellById(4L);

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
        List<Item> itemList = itemRepo.findAll();
        System.out.println(itemList);
        List<UUID> uuidList = itemList.stream().map(Item::getStorageId).toList();
        System.out.println(uuidList);
        ListStorageDto listStorageDto = storageFeign.getByList(new ListStorageDtoBuilder().setStorageClazz(Storage.class).setUuidList(uuidList).build()).getBody();

        return new ResponseEntity<>(mapToItemList(itemList, listStorageDto), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> category() {
        return new ResponseEntity<>(categoryRepo.findByCategoryName(CategoryName.CAR).get().getItems(), HttpStatus.OK);
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

//    private CarDto convertToCarDTO(Car car) {
//        return modelMapper.map(car, CarDto.class);
//    }

//    вариант добавить свой маппинг отдельных полей в классе
//    modelMapper.createTypeMap(Car.class, CarDTO.class)
//            .addMapping(src -> src.getPrice().getCustomPrice(), CarDTO::setPrice);


    private ListItemDto mapToItemList(List<Item> itemList, ListStorageDto listStorageDto) {
        Map<UUID, Storage> storageMap = new HashMap<>();
        for (Storage entity : listStorageDto.getStorageList()) {
            storageMap.put(entity.getId(), entity);
        }

        List<Item> newItemList = itemList.stream().map(el -> {
            el.setStorage(storageMap.get(el.getStorageId()));
            return el;
        }).toList();

        return new ListItemDtoBuilder().setItemList(newItemList).build();


    }
}