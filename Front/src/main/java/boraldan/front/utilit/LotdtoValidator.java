package boraldan.front.utilit;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;
import boraldan.entitymicro.shop.dto.LotDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

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
    }

    public void validateQuantityCart(CartDto cartDto, Object target, Errors errors) {
        LotDto lotDto = (LotDto) target;
        if (lotDto.getQuantity() == null) lotDto.setQuantity(0);

        if (lotDto.getQuantity() > lotDto.getLimitQuantity() || lotDto.getQuantity() < 1) {
            errors.rejectValue("quantity", "", "Ошибочное количество.");
            return;
        }
        Optional<CartUnitDto> cartUnitDto = cartDto.getCartUnitDtoList().stream()
                .filter(unit -> unit.getItem().getId().equals(lotDto.getItemId()))
                .findFirst();
        if (cartUnitDto.isPresent()) {
            if ((cartUnitDto.get().getQuantity() + lotDto.getQuantity()) > lotDto.getLimitQuantity()) {
                errors.rejectValue("quantity", "", "Нет на складе такого количества");
            }
        }
    }

}
