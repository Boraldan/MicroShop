package boraldan.front.utilit;

import boraldan.entitymicro.shop.dto.LotDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Валидатор для объектов типа LotDto.
 */
@Component
public class LotdtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LotDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LotDto lotDto = (LotDto) target;
        if (lotDto.getQuantity() > lotDto.getLimitQuantity()) {
            errors.rejectValue("quantity", "", "Нет такого количества");
        }
    }

}
