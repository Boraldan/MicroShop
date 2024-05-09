package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.storage.entity.Storage;

import java.util.UUID;

public class StorageBuilder {

    private final Storage storage;

    private StorageBuilder(Class<? extends Storage> clazz){
        this.storage = new Storage();
        this.storage.setStorageClazz(clazz);
    }

    public static StorageBuilder creat(Class<? extends Storage> clazz){
        return new StorageBuilder(clazz);
    }

    public StorageBuilder setId(UUID id) {
        this.storage.setId(id);
        return this;
    }

    public StorageBuilder setItemId(UUID itemId) {
        this.storage.setItemId(itemId);
        return this;
    }

//    public StorageBuilder setStorageClazz(Class<? extends Storage> storageClazz) {
//        this.storage.setStorageClazz(storageClazz);
//        return this;
//    }

    public StorageBuilder setQuantity(long quantity) {
        this.storage.setQuantity(quantity);
        return this;
    }

    public StorageBuilder setReserve(long reserve) {
        this.storage.setReserve(reserve);
        return this;
    }

    public Storage build() {
//        return this.convertTo(storage, clazz);
        return this.storage;
    }

//    private <T extends Storage> T convertTo(Storage storage, Class<? extends Storage> clazz) {
//        Type targetType = TypeFactory.rawClass(clazz);
//        return new ModelMapper().map(storage, targetType);
//    }


}
