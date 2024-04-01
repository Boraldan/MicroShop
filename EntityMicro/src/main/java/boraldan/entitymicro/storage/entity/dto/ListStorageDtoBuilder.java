package boraldan.entitymicro.storage.entity.dto;

import boraldan.entitymicro.storage.entity.Storage;

import java.util.List;

public class ListStorageDtoBuilder {

    private final ListStorageDto listStorageDto;

    public ListStorageDtoBuilder() {
        this.listStorageDto = new ListStorageDto();
    }

    public ListStorageDtoBuilder setList(List<?> list) {
        this.listStorageDto.setList(list.stream().map(el -> (Storage) el).toList());
        return this;
    }

    public ListStorageDto build(){
        return listStorageDto;
    }
}
