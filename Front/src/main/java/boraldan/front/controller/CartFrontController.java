package boraldan.front.controller;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.front.mq.account.MqOutFrontToAccountService;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.AccountRestClient;
import boraldan.front.rest_client.CartRestClient;
import boraldan.front.service.i_service.CartFrontService;
import boraldan.front.service.i_service.ItemFrontService;
import boraldan.front.utilit.CartDtoValidator;
import boraldan.front.utilit.CouponValidator;
import boraldan.front.utilit.LotdtoValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartFrontController {

    private final AccountRestClient accountRestClient;
    private final CartRestClient restClient;
    private final LotdtoValidator lotDtoValidator;
    private final CartDtoValidator cartDtoValidator;
    private final ItemFrontService itemFrontService;
    private final CartFrontService cartFrontService;
    private final RedisService redisService;
    private final HttpSession httpSession;
    private final MqOutFrontToAccountService mqOutFrontToAccountService;
    private final CouponValidator couponValidator;

    @PostMapping("/coupon/get")
    public String getCoupon(@ModelAttribute("cartDto") CartDto cartDto,
                            @Valid Coupon coupon, BindingResult bindingResult) {
        System.out.println(coupon);
        Coupon validCoupon = couponValidator.validateCoupon(coupon, bindingResult);

        if (bindingResult.hasErrors()) {
            return "cart";
        }

        cartDto.setCoupon(validCoupon);
        updateRedisCartDto(cartDto);

        return "redirect:/cart/show";

    }

    @GetMapping("/coupon/del")
    public String deleteCoupon(@ModelAttribute("cartDto") CartDto cartDto) {
        cartDto.setCoupon(null);
        updateRedisCartDto(cartDto);
        return "redirect:/cart/show";

    }


    // @PreAuthorize("isAuthenticated()")  // не отправляет на аутентификацию, а просто блокирует доступ к методу
    @PostMapping("/checkout")
    public String checkoutCart(Principal principal,
                               @ModelAttribute("cartDto") CartDto cartDto, BindingResult bindingResult) {

        String cartReserveKey = String.format("%s_reserve", principal.getName().toLowerCase());
        CartDto reserveCartDto = redisService.getCart(cartReserveKey);
        if (reserveCartDto != null) {
            return "checkout";
        }
        cartDtoValidator.validate(cartDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/cart/show";
        }
        cartFrontService.setReserveWithInterrupt(cartDto); // логика перевода товаров в резерв
        return "checkout";
    }

    @GetMapping("/booked")
    public String creatOrder(Model model,
                             Principal principal,
                             @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient) {

        String cartReserveKey = String.format("%s_reserve", principal.getName().toLowerCase());
        CartDto reserveCartDto = redisService.getCart(cartReserveKey);
        if (reserveCartDto == null) {
            return "redirect:/cart/show";
        }

//// вариант с отправкой через Rabbit
//        String jwtToken = authorizedClient.getAccessToken().getTokenValue();
//        mqOutFrontToAccountService.sendMessage(reserveCartDto, jwtToken); // Rabbit отправляем на оформление ордера

        Order order = accountRestClient.creatOrder(reserveCartDto);
        model.addAttribute("order", order);

        cartFrontService.interruptReserveTimerByBooked(reserveCartDto); // прерываем таймер резерва и очищаем корзину
        model.addAttribute("cartDto", redisService.getCart(principal.getName().toLowerCase()));

        return "booked";
    }

    @GetMapping("/show")
    public String showCart(Model model,
                           @ModelAttribute("cartDto") CartDto cartDto, BindingResult bindingResult) {

        cartDtoValidator.validate(cartDto, bindingResult);
        model.addAttribute("coupon", new Coupon());
        return "cart";
    }


    @PostMapping("/item/add")
    public String addItemToCart(Model model,
                                @ModelAttribute("cartDto") CartDto cartDto,
                                @ModelAttribute("lotDto") @Valid LotDto lotDto, BindingResult bindingResult) {

        Item item = itemFrontService.getAndConvertItem(lotDto.getItemId(), lotDto.getItemClassName());
        lotDtoValidator.validateQuantityCart(cartDto, lotDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            List<String> fieldNames = itemFrontService.getFieldNames(item, item.getItemClazz());
            model.addAttribute("fieldNames", fieldNames);
            return "item";
        }

        CartDto updateCartDto = cartFrontService.addItemToCart(cartDto, item, lotDto);
        updateRedisCartDto(cartDto, updateCartDto);
        return "redirect:/shop/item?itemId=%s&itemClassName=%s".formatted(lotDto.getItemId().toString(), lotDto.getItemClassName());
    }

    @GetMapping("/item/del")
    public String delItemToCart(@ModelAttribute("cartDto") CartDto cartDto,
                                @RequestParam(name = "itemId", required = false) UUID itemId) {
        if (itemId == null) {
            return "redirect:/catalog";
        }
        CartDto updateCartDto = cartFrontService.delItemFromCart(cartDto, itemId);
        updateRedisCartDto(cartDto, updateCartDto);
        return "redirect:/catalog";
    }

//  вложенные формы не срабатваеют
//    @PostMapping("/item/del")
//    public String delItemToCart(@ModelAttribute("cartDto") CartDto cartDto,
//                                @ModelAttribute("itemId") UUID itemId) {
//        CartDto updateCartDto = cartFrontService.deleteFromCart(cartDto, itemId);
//        redisUpdateCartDto(cartDto, updateCartDto);
//        return "redirect:/catalog";
//    }

    private void updateRedisCartDto(CartDto oldCartDto, CartDto updateCartDto) {
        if (oldCartDto.getCustomer() != null) {
            redisService.setCart(oldCartDto.getCustomer().getUsername().toLowerCase(), updateCartDto);
        } else {
            redisService.setCart(httpSession.getId(), updateCartDto);
        }
    }

    private void updateRedisCartDto(CartDto cartDto) {
        if (cartDto.getCustomer() != null) {
            redisService.setCart(cartDto.getCustomer().getUsername().toLowerCase(), cartDto);
        } else {
            redisService.setCart(httpSession.getId(), cartDto);
        }
    }

}
