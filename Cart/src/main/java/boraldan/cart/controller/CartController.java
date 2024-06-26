package boraldan.cart.controller;

import boraldan.cart.service.CartService;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public ResponseEntity<CartListDTO> carts() {
        return new ResponseEntity<>(new CartListDTO(cartService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<CartDto> getCartByCustomer(@RequestBody Customer customer) {
        CartDto cartDto = cartService.getCartDtoByCustomer(customer);
        System.out.println("   @PatchMapping(/get) -- > " + cartDto);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/save")
    public ResponseEntity<Void> saveCartDto(@RequestBody CartDto cartDto) {
        System.out.println("   @PatchMapping(/save) -- > " + cartDto);
        cartService.convertToCartAndSave(cartDto);
        return ResponseEntity.noContent().build();
    }

//    private final OrderServiceImpl orderServiceImpl;
//    private final EmailService emailService;
//    private final CouponValid couponValid;
//    private final SellerService sellerService;
////    private final PayService payService;
//    /**
//     * Интерфейс Spring Cloud OpenFeign для сервиса Bank
//     */
//    private final BankFeign bankFeign;

//    @GetMapping("/cart")
//    public String shop(@RequestParam(value = "car", required = false) Integer idCar, Model model) {
//        if (idCar != null) {
//            cartService.dellItem(idCar);
//            return "redirect:/cart";
//        }
//        session.setAttribute("oldCarList", cartService.cloneCart());
//        model.addAttribute("coupon", new Coupon());
//        return "cart";
//    }
//
//    @PostMapping("/cart")
//    public String checkLot(@ModelAttribute("cart") Cart cart, BindingResult bindingResult) {
//        List<Car> oldCarList = (List<Car>) session.getAttribute("oldCarList");
//        if (cartService.checkFalseLot(cart, oldCarList)) {
//            return "redirect:/cart";
//        }
//        cartService.updateLotCart(cart, oldCarList);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/cart/coupon")
//    public String checkCoupon(@ModelAttribute("coupon") @Valid Coupon coupon, BindingResult bindingResult) {
//        coupon = couponValid.validateCoupon(coupon, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "cart";
//        }
//        Cart cart = (Cart) session.getAttribute("cart");
//        cart.setCoupon(coupon);
//        return "redirect:/cart";
//    }
//
//    @GetMapping("/checkout")
//    public String checkout(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart.getCarList().isEmpty()) {
//            return "redirect:/cart";
//        }
//        model.addAttribute("seller", sellerService.findById(1).get());
//        model.addAttribute("person", personDetails.getPerson());
//        model.addAttribute("payDTO", new PayDTO());
//        return "/checkout";
//    }
//
//    @GetMapping("/thankyou")
//    public String creatOrder(Model model) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart.getCarList().isEmpty()) {
//            return "redirect:/cart";
//        }
//        Orders orders = orderServiceImpl.saveOrdersCar(cart);
//        model.addAttribute("Order", orders);
//        emailService.sendMessageThread(orders);
//
//        cart.getCarList().clear();
//        cart.setCoupon(null);
//        return "/thankyou";
//    }
//
//    /**
//     * Завершаем оформление ордера. Внедряем BankFeign для оплаты
//     *
//     * @param model
//     * @param payDTO объект для оплаты
//     * @return
//     */
//    @PostMapping("/thankyou")
//    public String creatAndPayOrder(Model model, @ModelAttribute("payDTO") PayDTO payDTO) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart.getCarList().isEmpty()) {
//            return "redirect:/cart";
//        }
//        Orders orders = orderServiceImpl.saveOrdersCar(cart);
//        try {
//            HttpStatusCode httpStatus = bankFeign.transferShop(payDTO).getStatusCode();
////            HttpStatusCode httpStatus = payService.sendPayOrder(payDTO).getStatusCode();
//            if (httpStatus.is2xxSuccessful()) {
//                orders.setStatus(Status.PAID);
//            }
//            System.out.println("Статус ордера --> " + orders);
//        } catch (FeignException e) {
//            System.out.println(e.getMessage());
//        }
//        emailService.sendMessageThread(orders);
//        model.addAttribute("Order", orders);
//
//        cart.getCarList().clear();
//        cart.setCoupon(null);
//        return "/thankyou";
//    }
//
//    @GetMapping("/orders/cancel")
//    public String canselOrders(@RequestParam(value = "id", required = false) Integer id) {
//        if (id != null) {
//            orderServiceImpl.cancelOrders(id);
//            return "redirect:/auth/account";
//        }
//        return "redirect:/auth/account";
//    }

}
