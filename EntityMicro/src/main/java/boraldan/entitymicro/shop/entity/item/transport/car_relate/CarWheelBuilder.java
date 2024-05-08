package boraldan.entitymicro.shop.entity.item.transport.car_relate;

import boraldan.entitymicro.shop.entity.price.item_price.CarWheelPrice;
import boraldan.entitymicro.storage.entity.Storage;

public class CarWheelBuilder {

    private final CarWheel carWheel;

    private CarWheelBuilder() {
        this.carWheel = new CarWheel();
    }

    public static CarWheelBuilder create() {
        return new CarWheelBuilder();
    }

    public CarWheelBuilder setName(String name) {
        this.carWheel.setName(name);
        return this;
    }

    public CarWheelBuilder setDiameter(Double diameter) {
        this.carWheel.setDiameter(diameter);
        return this;
    }

    public CarWheelBuilder setSeason(String season) {
        this.carWheel.setSeason(season);
        return this;
    }

    public CarWheelBuilder setPrice(CarWheelPrice price) {
        this.carWheel.setPrice(price);
        return this;
    }

    public CarWheelBuilder setStorage(Storage storage) {
        this.carWheel.setStorage(storage);
        return this;
    }

    public CarWheel build() {
        this.carWheel.initItemTitle();
        return this.carWheel;
    }
}
