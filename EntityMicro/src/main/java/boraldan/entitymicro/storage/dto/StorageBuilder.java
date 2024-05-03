package boraldan.entitymicro.storage.dto;

import boraldan.entitymicro.storage.entity.Storage;

import java.util.UUID;

public class StorageBuilder {

    private final Storage storage;

    public StorageBuilder(){
        this.storage = new Storage();
    }

    public StorageBuilder setId(UUID id) {
        this.storage.setId(id);
        return this;
    }

    public StorageBuilder setItemId(Long itemId) {
        this.storage.setItemId(itemId);
        return this;
    }

    public StorageBuilder setStorageClazz(Class<?> storageClazz) {
        this.storage.setStorageClazz(storageClazz);
        return this;
    }

    public StorageBuilder setQuantity(long quantity) {
        this.storage.setQuantity(quantity);
        return this;
    }

    public StorageBuilder setReserve(long reserve) {
        this.storage.setReserve(reserve);
        return this;
    }

    public Storage build(){
        return this.storage;
    }
}
