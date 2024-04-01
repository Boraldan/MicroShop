package boraldan.storage.controller;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.service.MapStorageService;
import boraldan.storage.service.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

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
        return new ResponseEntity<>((Storage) storageService.get(storageDto.getClazz()).getById(storageDto.getId()),
                HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<ListStorageDto> getAll(@RequestBody StorageDto storageDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>(new ListStorageDtoBuilder().
                setList(storageService.get(storageDto.getClazz()).getAll()).build(), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Storage> saveItem(@RequestBody StorageDto storageDto) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>((Storage) storageService.get(storageDto.getClazz()).save(storageDto), HttpStatus.OK);
    }

    @DeleteMapping("/dell")
    public ResponseEntity<Void> dellItem(@RequestBody StorageDto storageDto) {
        //TODO добавить валидацию storageDto
        storageService.get(storageDto.getClazz()).delete(storageDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/car2")
    public ResponseEntity<?> car2(@RequestParam("id") UUID id) {
        //TODO добавить валидацию storageDto
        return new ResponseEntity<>((Storage) storageService.get(CarStorage.class).getById(id), HttpStatus.OK);
    }


}
