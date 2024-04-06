package boraldan.shop.service.old;

//@Service
//@RequiredArgsConstructor
//public class CarServiceV1 implements CarService {
//
//    private final CarRepo carRepo;
//    private final StorageFeign storageFeign;
//
//    @Override
//    public Optional<Car> getById(Long id) {
//        return carRepo.findById(id);
//    }
//
//    public List<Car> getAll() {
//        return carRepo.findAll();
//    }
//
//    @Transactional
//    public Car save(Car car) {
//        return carRepo.save(car);
//    }
//
//
//    @Transactional
//    public void dellById(Long id){
//        Car car = carRepo.findById(id).get();
//        StorageDto storageDto = new StorageDtoBuilder().setId(car.getStorageId()).setStorageClazz(car.getStorageClazz()).build();
//        storageFeign.dellItem(storageDto);
//        carRepo.deleteById(id);
//    }
//
//}
