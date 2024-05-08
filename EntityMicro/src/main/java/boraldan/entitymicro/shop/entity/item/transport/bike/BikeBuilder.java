package boraldan.entitymicro.shop.entity.item.transport.bike;

import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.entitymicro.storage.entity.Storage;


public class BikeBuilder {

    private final Bike bike;

    private BikeBuilder() {
        this.bike = new Bike();
    }

    public static BikeBuilder create() {
        return new BikeBuilder();
    }

    public BikeBuilder setName(String name) {
        this.bike.setName(name);
        return this;
    }

    public BikeBuilder setFactory(String factory) {
        this.bike.setFactory(factory);
        return this;
    }

    public BikeBuilder setWheels(Integer wheels) {
        this.bike.setWheels(wheels);
        return this;
    }
    public BikeBuilder setYear(Integer year) {
        this.bike.setYear(year);
        return this;
    }
    public BikeBuilder setFuel(Fuel fuel) {
        this.bike.setFuel(fuel);
        return this;
    }

    public BikeBuilder setPrice(BikePrice price) {
        this.bike.setPrice(price);
        return this;
    }

    public BikeBuilder setStorage(Storage storage) {
        this.bike.setStorage(storage);
        return this;
    }

    public Bike build() {
        this.bike.initItemTitle();
        return this.bike;
    }
}
