package boraldan.entitymicro.storage.entity.dto;

import java.util.UUID;

public class StorageDtoBuilder {

    private final StorageDto storageDto;

    public StorageDtoBuilder() {
        this.storageDto = new StorageDto();
    }

    public StorageDtoBuilder setId(UUID id) {
        this.storageDto.setId(id);
        return this;
    }

    public StorageDtoBuilder setClazz(Class<?> clazz) {
        this.storageDto.setClazz(clazz);
        return this;
    }

    public StorageDtoBuilder setQuantity(long quantity) {
        this.storageDto.setQuantity(quantity);
        return this;
    }

    public StorageDtoBuilder setReserve(long reserve) {
        this.storageDto.setReserve(reserve);
        return this;
    }

    public StorageDto build(){
        return this.storageDto;
    }
}
