package boraldan.storage.service.old;

//@RequiredArgsConstructor
//@Service
//public class CarWheelStorageServiceV1   {

//    private final CarWheelStorageRepo carWheelStorageRepo;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public CarWheelStorage getByItemId(Long itemId) {
//        return carWheelStorageRepo.findByItemId(itemId).orElse(null);
//    }
//
//    @Override
//    public CarWheelStorage saveNew(StorageDto storageDto) {
//        if (storageDto.getItemId() == null) {
//            return carWheelStorageRepo.save(convertToStorageItem(storageDto));
//        }
//
//        //TODO  подумать, что делать, если пришел объект с id, который уже есть в базе
//        return convertToStorageItem(storageDto);
//    }
//
//
//    private CarWheelStorage convertToStorageItem(StorageDto storageDto) {
//        return modelMapper.map(storageDto, CarWheelStorage.class);
//    }

//}
