package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.storage.dto.ReserveDtoList;
import boraldan.entitymicro.storage.dto.ReserveDto;

import java.util.List;

public class ReserveDtoListBuilder {

    private final ReserveDtoList reserveDtoList;

    private ReserveDtoListBuilder() {
        this.reserveDtoList = new ReserveDtoList();
    }

    public static ReserveDtoListBuilder creat() {
        return new ReserveDtoListBuilder();
    }

    public ReserveDtoListBuilder setReserveDtoList(List<ReserveDto> reserveDtoList) {
        this.reserveDtoList.setReserveDtoList(reserveDtoList);
        return this;
    }

    public ReserveDtoList build() {
        return this.reserveDtoList;
    }
}
