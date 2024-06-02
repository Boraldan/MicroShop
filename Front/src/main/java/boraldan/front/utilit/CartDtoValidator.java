package boraldan.front.utilit;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CartDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CartDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

            CartDto cartDto = (CartDto) target;

            if (cartDto.getCartUnitDtoList() != null) {
                for (int i = 0; i < cartDto.getCartUnitDtoList().size(); i++) {
                    CartUnitDto cartUnitDto = cartDto.getCartUnitDtoList().get(i);
                    if (cartUnitDto.getUnitQuantity() <= 0) {
                        errors.rejectValue("cartUnitDtoList[" + i + "].unitQuantity",
                                "quantity.negativeOrZero",
                                "Quantity must be greater than zero");
                    } else if (cartUnitDto.getUnitQuantity() > cartUnitDto.getItem().getStorage().getQuantity()) {
                        errors.rejectValue("cartUnitDtoList[" + i + "].unitQuantity",
                                "quantity.exceedsStorage",
                                "The available quantity is %d pcs"
                                        .formatted(cartUnitDto.getItem().getStorage().getQuantity()));
                    }
                }
            }
    }
}