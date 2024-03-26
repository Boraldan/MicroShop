package boraldan.bank.util;


import boraldan.entitymicro.bank.dto.PayDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidPayDTO {

    public boolean falsePayDTO(PayDTO payDTO) {
        return payDTO.getBayerCard() == null || payDTO.getSellerCard() == null || payDTO.getAmount() == null;
    }
}
