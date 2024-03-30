package boraldan.storage.service.old;

//@RequiredArgsConstructor
//@Service
//public class StorageServiceV1 implements StorageService<Storage, StorageJpaRepository<Storage>> {

//    private final StorageRepo storageRepo;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public StorageJpaRepository<Storage> getStorageRepo() {
//        return storageRepo;
//    }
//
//    @Override
//    public Storage getByItemId(Long itemId) {
//        return StorageService.super.getByItemId(itemId);
//    }
//
//    public List<Storage> getAll(){
//        return storageRepo.findAll();
//    }
//
//    @Override
//    public Storage saveNew(StorageDto storageDto) {
//        return StorageService.super.saveNew(storageDto);
//    }
//
//    @Override
//    public Storage convertToStorageItem(StorageDto storageDto) {
//        return modelMapper.map(storageDto, Storage.class);
//    }
//}
