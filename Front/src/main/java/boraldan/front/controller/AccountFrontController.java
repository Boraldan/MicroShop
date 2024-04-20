package boraldan.front.controller;

import boraldan.entitymicro.account.dto.PersonDTO;
import boraldan.front.rest_client.AccountRestClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountFrontController {

    private final AccountRestClient restClient;
    private final HttpSession httpSession;


    @GetMapping
    public String getAccount(Model model) {
        List<String> customers = restClient.getAccount();
        log.info("1 --> " + customers);
        model.addAttribute("customers", customers);
        return "account";
    }

    @GetMapping("/add")
    public String addAccount(Model model) {
        PersonDTO userDTO = restClient.addAccount();
        log.info("1 --> " + userDTO);
        model.addAttribute("user", userDTO);
        return "UserDto";
    }


}
