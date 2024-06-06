package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.storage.dto.ReserveDto;

import java.util.UUID;

public class ReserveDtoBuilder {

    private final ReserveDto reserve;

    private ReserveDtoBuilder() {
        this.reserve = new ReserveDto();
    }

    public static ReserveDtoBuilder creat() {
        return new ReserveDtoBuilder();
    }

    public ReserveDtoBuilder setItemId(UUID itemId) {
        this.reserve.setItemId(itemId);
        return this;
    }

    public ReserveDtoBuilder setReserve(Integer reserve) {
        this.reserve.setCartReserve(reserve);
        return this;
    }

    public ReserveDto build() {
        return this.reserve;
    }
}
