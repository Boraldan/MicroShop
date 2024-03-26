package boraldan.entitymicro.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class PayDTO {
    @NotNull
    private Long bayerCard;
    @NotNull
    private Long sellerCard;
    @NotNull
    private BigDecimal amount;
}
