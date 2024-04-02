package boraldan.storage.controller;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.service.MapStorageService;
import boraldan.storage.service.StorageService;
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
    public StorageController(MapStorageService mapStorageService) {
        this.storageService = mapStorageService.getMapBean();
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
    public ResponseEntity<Storage> getQuantity(@RequestBody StorageDto storageDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageService.get(storageDto.getStorageClazz()).getById(storageDto.getId()),
                HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ListStorageDto> getList(@RequestBody ListStorageDto listStorageDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(storageService
                .get(listStorageDto.getStorageClazz() == null ? Storage.class : listStorageDto.getStorageClazz())
                .getByList(listStorageDto), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Storage> saveItem(@RequestBody StorageDto storageDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>( storageService.get(storageDto.getStorageClazz()).save(storageDto), HttpStatus.OK);
    }

    @DeleteMapping("/dell")
    public ResponseEntity<Void> dellItem(@RequestBody StorageDto storageDto) {
        //TODO добавить валидацию storageDto
        storageService.get(storageDto.getStorageClazz()).delete(storageDto);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/car")
//    public ResponseEntity<?> car2() {
//        //TODO добавить валидацию storageDto
//
//        return new ResponseEntity<>(new ListStorageDtoBuilder().
//                setList(storageService.get(CarStorage.class).getAll()).build(), HttpStatus.OK);
//    }


}
