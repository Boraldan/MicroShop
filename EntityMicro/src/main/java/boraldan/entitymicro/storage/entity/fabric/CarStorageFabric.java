package boraldan.entitymicro.storage.entity.fabric;

import boraldan.entitymicro.storage.entity.transport.car.CarStorage;


public class CarStorageFabric {

    private CarStorage carStorage;

    public CarStorageFabric(){
        this.carStorage = new CarStorage();
    }

    public CarStorageFabric setCarQuantity(Long quantity){
        this.carStorage.setQuantity(quantity);
        return this;
    }
    public CarStorageFabric setCarReserve(Long reserve){
        this.carStorage.setQuantity(reserve);
        return this;
    }

    public CarStorage build(){
        return this.carStorage;
    }
}
