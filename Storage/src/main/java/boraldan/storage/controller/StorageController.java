package boraldan.storage.controller;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.storage.dto.ReserveDtoList;
import boraldan.entitymicro.storage.dto.StorageListDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.storage.service.StorageServiceProviderByCategory;
import boraldan.storage.service.StorageServiceProviderByClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
public class StorageController {
    //    private final Map<Class<?>, StorageService<?, ?>> storageService;
//    private final Map<CategoryName, StorageService<?, ?>> storageServiceCategory;
    private final StorageServiceProviderByClass storageServiceClass;
    private final StorageServiceProviderByCategory storageServiceCategory;


//    @Autowired
//    public StorageController(StorageServiceProviderByClass storageServiceProviderByClass,
//                             StorageServiceProviderByCategory storageServiceProviderByCategory) {
//        this.storageService = storageServiceProviderByClass.getMapBeanStorage();
//        this.storageServiceCategory = storageServiceProviderByCategory.getMapBeanStorage();
//    }

//    внедрение Map через рефлексию, пока не работает
//    private final BeanExtractor beanExtractor;
//    Map<Class<?>, StorageService<?, StorageJpaRepository<?>>> beanMap ;
//
//    @PostConstruct
//    public void initBeanMap (){
//       this.beanMap = beanExtractor.extractStorageServicesFromClass(ServiceStorageConfig.class);
//    }


    @PatchMapping("/reserve/set")
    public ResponseEntity<Void> setReserve(@RequestBody ReserveDtoList reserveDtoList) {
        System.out.println("/reserve/set --> " + reserveDtoList);
        storageServiceClass.getService(Storage.class).setReserve(reserveDtoList);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reserve/del")
    public ResponseEntity<Void> deleteReserve(@RequestBody ReserveDtoList reserveDtoList) {
        System.out.println("/reserve/delete --> " + reserveDtoList);
        storageServiceClass.getService(Storage.class).deleteReserve(reserveDtoList);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/quantity")
    public ResponseEntity<Storage> getQuantity(@RequestBody UUID itemId) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageServiceClass
                .getService(Storage.class)
                .getByItemId(itemId).orElse(null), HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<StorageListDto> getList(@RequestBody StorageListDto storageListDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageServiceClass
                .getService(storageListDto.getStorageClazz() == null ? Storage.class : storageListDto.getStorageClazz())
                .getByList(storageListDto), HttpStatus.OK);
    }

    @PostMapping("/listbycategory")
    public ResponseEntity<StorageListDto> getListByCategory(@RequestBody StorageListDto storageListDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageServiceCategory
                .getService(storageListDto.getCategoryName() == null ? CategoryName.ITEM : storageListDto.getCategoryName())
                .getByList(storageListDto), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Storage> saveItem(@RequestBody Storage storage) {
        //TODO добавить валидацию storageDto
        System.out.println(storage.getClass().getSimpleName());
        return new ResponseEntity<>(storageServiceClass.getService(storage.getStorageClazz()).save(storage), HttpStatus.OK);
    }

    @DeleteMapping("/dell")
    public ResponseEntity<Void> dellItem(@RequestBody UUID itemId) {
        //TODO добавить валидацию storageDto
        storageServiceClass.getService(Storage.class).deleteByItemId(itemId);
        return ResponseEntity.ok().build();
    }


}
