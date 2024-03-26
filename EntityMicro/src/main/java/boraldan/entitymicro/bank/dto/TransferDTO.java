package boraldan.entitymicro.bank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {

  private long senderAccountId;
  private long receiverAccountId;
  private BigDecimal amount;

}
