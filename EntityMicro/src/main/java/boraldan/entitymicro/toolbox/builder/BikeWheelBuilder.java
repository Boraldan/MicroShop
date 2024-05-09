package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.entity.item.transport.bike_relate.BikeWheel;
import boraldan.entitymicro.shop.entity.price.item_price.BikeWheelPrice;
import boraldan.entitymicro.storage.entity.Storage;

public class BikeWheelBuilder {

    private final BikeWheel bikeWheel;

    private BikeWheelBuilder() {
        this.bikeWheel = new BikeWheel();
    }

    public static BikeWheelBuilder create() {
        return new BikeWheelBuilder();
    }

    public BikeWheelBuilder setName(String name) {
        this.bikeWheel.setName(name);
        return this;
    }

    public BikeWheelBuilder setDiameter(Double diameter) {
        this.bikeWheel.setDiameter(diameter);
        return this;
    }

    public BikeWheelBuilder setSeason(String season) {
        this.bikeWheel.setSeason(season);
        return this;
    }


    public BikeWheelBuilder setPrice(BikeWheelPrice price) {
        this.bikeWheel.setPrice(price);
        return this;
    }

    public BikeWheelBuilder setStorage(Storage storage) {
        this.bikeWheel.setStorage(storage);
        return this;
    }

    public BikeWheel build() {
        this.bikeWheel.initItemTitle();
        return this.bikeWheel;
    }
}
