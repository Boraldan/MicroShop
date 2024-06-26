package boraldan.front.controller;

import boraldan.entitymicro.account.dto.PersonDTO;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.AccountRestClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountFrontController {

    private final AccountRestClient restClient;
    private final RedisService redisService;
    private final HttpSession httpSession;


    @GetMapping("/customer/orders")
    public String getOrderCustomer(Model model,
                                   @ModelAttribute("cartDto") CartDto cartDto) {
        Customer customer = restClient.getCustomerAccountWhitOrder();
        model.addAttribute("customer", customer);
        return "order";
    }


    @GetMapping("/kc/all")
    public String getAccount(Model model) {
        List<String> customers = restClient.getAccount();
        model.addAttribute("customers", customers);
        return "accounts";
    }

    @GetMapping("/customer")
    public String getCustomer(Model model) {
        Customer customer = restClient.getCustomerAccount();
        model.addAttribute("customer", customer);
        return "customer";
    }


    @GetMapping("/kc/add")
    public String addAccount(Model model) {
        PersonDTO personDTO = restClient.addAccount();
        model.addAttribute("user", personDTO);
        return "UserDto";
    }


}
