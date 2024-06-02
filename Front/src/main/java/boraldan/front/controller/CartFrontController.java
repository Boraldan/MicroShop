package boraldan.front.controller;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.CartRestClient;
import boraldan.front.service.i_service.CartFrontService;
import boraldan.front.service.i_service.ItemFrontService;
import boraldan.front.utilit.CartDtoValidator;
import boraldan.front.utilit.LotdtoValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartFrontController {

    private final CartRestClient restClient;
    private final LotdtoValidator lotDtoValidator;
    private final ItemFrontService itemFrontService;
    private final CartFrontService cartFrontService;
    private final RedisService redisService;
    private final HttpSession httpSession;


    private final CartDtoValidator cartDtoValidator;

    @GetMapping("/show")
    public String showCart(@ModelAttribute("cartDto") CartDto cartDto, BindingResult bindingResult) {
        cartDtoValidator.validate(cartDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "cart";
        }
        return "cart";
    }

    @PostMapping("/item/add")
    public String addItemToCart(Model model,
                                @ModelAttribute("cartDto") CartDto cartDto,
                                @ModelAttribute("lotDto") @Valid LotDto lotDto,
                                BindingResult bindingResult) {
        Item item = itemFrontService.getAndConvertItem(lotDto.getItemId(), lotDto.getItemClassName());
        lotDtoValidator.validateQuantityCart(cartDto, lotDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            List<String> fieldNames = itemFrontService.getFieldNames(item, item.getItemClazz());
            model.addAttribute("fieldNames", fieldNames);
            return "item";
        }

        System.out.println("/item/add -->  " + item);

        CartDto updateCartDto = cartFrontService.addToCart(cartDto, item, lotDto);
        redisUpdateCartDto(cartDto, updateCartDto);
        return "redirect:/shop/item?itemId=%s&itemClassName=%s".formatted(lotDto.getItemId().toString(), lotDto.getItemClassName());
    }

    @PostMapping("/item/del")
    public String addItemToCart(@ModelAttribute("cartDto") CartDto cartDto,
                                @ModelAttribute("itemId") UUID itemId) {
        CartDto updateCartDto = cartFrontService.deleteFromCart(cartDto, itemId);
        redisUpdateCartDto(cartDto, updateCartDto);
        return "redirect:/catalog";
    }

    private void redisUpdateCartDto(CartDto oldCartDto, CartDto updateCartDto) {
        if (oldCartDto.getCustomer() != null) {
            redisService.setCart(oldCartDto.getCustomer().getUsername().toLowerCase(), updateCartDto);
        } else {
            redisService.setCart(httpSession.getId(), updateCartDto);
        }
    }

}
