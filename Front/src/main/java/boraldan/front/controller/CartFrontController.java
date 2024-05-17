package boraldan.front.controller;

import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.front.rest_client.CartRestClient;
import boraldan.front.utilit.LotdtoValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartFrontController {

    private final CartRestClient restClient;
    private final LotdtoValidator lotDtoValidator;

    @PostMapping("/item/add")
    public String addItemToCart(Model model,  @Valid LotDto lotDto, BindingResult bindingResult) {


        // TODO: 14.05.2024 как отобразить неправильный ввод количества
        lotDtoValidator.validate(lotDto, bindingResult);
        if (bindingResult.hasErrors()){
            return "redirect:/catalog";
        }

        System.out.println(lotDto);

        return "redirect:/catalog";
    }


}
