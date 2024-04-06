package boraldan.storage.controller;

import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.storage.service.StorageService;
import boraldan.storage.service.StorageServiceProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
public class StorageController {
    private final Map<Class<?>, StorageService<?, ?>> storageService;

    @Autowired
    public StorageController(StorageServiceProvider storageServiceProvider) {
        this.storageService = storageServiceProvider.getMapBeanStorage();
    }

//    внедрение Map через рефлексию, пока не работает
//    private final BeanExtractor beanExtractor;
//    Map<Class<?>, StorageService<?, StorageJpaRepository<?>>> beanMap ;
//
//    @PostConstruct
//    public void initBeanMap (){
//       this.beanMap = beanExtractor.extractStorageServicesFromClass(ServiceStorageConfig.class);
//    }

    @PostMapping("/quantity")
    public ResponseEntity<Storage> getQuantity(@RequestBody Storage storage) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageService
                .get(storage.getStorageClazz() == null ? Storage.class : storage.getStorageClazz())
                .getById(storage.getId()), HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ListStorageDto> getList(@RequestBody ListStorageDto listStorageDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageService
                .get(listStorageDto.getStorageClazz() == null ? Storage.class : listStorageDto.getStorageClazz())
                .getByList(listStorageDto), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity< Storage> saveItem(@RequestBody  Storage storage) {
        //TODO добавить валидацию storageDto
        System.out.println(storage.getStorageClazz());
        return new ResponseEntity<>(storageService.get(storage.getStorageClazz()).save(storage), HttpStatus.OK);
    }

    @DeleteMapping("/dell")
    public ResponseEntity<Void> dellItem(@RequestBody Storage storage) {
        //TODO добавить валидацию storageDto
        storageService.get(storage.getStorageClazz() == null ?  Storage.class : storage.getStorageClazz()).delete(storage);
        return ResponseEntity.ok().build();
    }


}
