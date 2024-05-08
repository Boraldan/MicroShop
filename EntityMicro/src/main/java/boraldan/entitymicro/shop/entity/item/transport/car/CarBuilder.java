package boraldan.entitymicro.shop.entity.item.transport.car;


import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.storage.entity.Storage;


public class CarBuilder {

    private final Car car;

    private CarBuilder() {
        this.car = new Car();
    }

    public static CarBuilder create() {
        return new CarBuilder();
    }

    public CarBuilder setName(String name) {
        this.car.setName(name);
        return this;
    }

    public CarBuilder setFactory(String factory) {
        this.car.setFactory(factory);
        return this;
    }

    public CarBuilder setYear(Integer year) {
        this.car.setYear(year);
        return this;
    }

    public CarBuilder setTypes(Types types) {
        this.car.setTypes(types);
        return this;
    }

    public CarBuilder setFuel(Fuel fuel) {
        this.car.setFuel(fuel);
        return this;
    }

    public CarBuilder setPrice(CarPrice price) {
        this.car.setPrice(price);
        return this;
    }

    public CarBuilder setStorage(Storage storage) {
        this.car.setStorage(storage);
        return this;
    }

    public Car build() {
        this.car.initItemTitle();
        return this.car;
    }
}