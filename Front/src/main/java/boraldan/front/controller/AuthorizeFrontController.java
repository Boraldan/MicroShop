package boraldan.front.controller;

import boraldan.entitymicro.account.dto.SingUpDto;
import boraldan.front.rest_client.AuthorizeRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/authorize")
public class AuthorizeFrontController {

    private final AuthorizeRestClient restClient;

    @GetMapping("/singup")
    public String singUpCustomer() {
        return "auth/singup";
    }

    @PostMapping("/singup")
    public String postCustomer(Model model, SingUpDto singUpDto) {
        model.addAttribute("customer", restClient.singUpCustomer(singUpDto));
        return "customer";
    }

    @GetMapping("/oauth2/code/keycloak")
    public String keycloak(Model model) {
        return "index";
    }
}
