package boraldan.storage.service.old;


//@RequiredArgsConstructor
//@Service
//public class CarStorageServiceV1 implements StorageService<CarStorage, StorageJpaRepository<CarStorage>> {
//
//    private final CarStorageRepo carStorageRepo;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public StorageJpaRepository<CarStorage> getStorageRepo() {
//        return this.carStorageRepo;
//    }
//
//    @Override
//    public CarStorage getByItemId(Long itemId) {
//        CarStorage carStorage =  StorageService.super.getByItemId(itemId);
//        System.out.println(carStorage);
//        return carStorage;
////        return StorageService.super.getByItemId(itemId);
//    }
//
//    @Override
//    public CarStorage saveNew(StorageDto storageDto) {
//        return StorageService.super.saveNew(storageDto);
//    }
//
//    @Override
//    public CarStorage convertToStorageItem(StorageDto storageDto) {
//        return modelMapper.map(storageDto, CarStorage.class);
//    }

//    private final CarStorageRepo carStorageRepo;
//
//    public CarStorage getByItemId(Long itemId){
//        return carStorageRepo.findByItemId(itemId).orElse(null);
//    }
//
//    @Override
//    public CarStorage saveNew(StorageDto storageDto) {
//        return null;
//    }


//}
