package boraldan.front.controller;


import boraldan.entitymicro.account.dto.UserDTO;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.front.client.RestClientProductsRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class FrontController {

    private final RestClientProductsRestClient restClient;

//    private final OAuth2AuthorizedClientManager authorizedClientManager;

//    public FrontController(ClientRegistrationRepository clientRegistrationRepository,
//                           OAuth2AuthorizedClientRepository authorizedClientRepository) {
//        this.authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
//                clientRegistrationRepository, authorizedClientRepository);
//        this.restClient = RestClient.builder()
//                .baseUrl("http://localhost:8765/")
////                .requestInterceptor((request, body, execution) -> {
////                    if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
////                        var token = this.authorizedClientManager.authorize(
////                                        OAuth2AuthorizeRequest.withClientRegistrationId("authorization-grant-type")
////                                                .principal(SecurityContextHolder.getContext().getAuthentication())
////                                                .build())
////                                .getAccessToken().getTokenValue();
////                        request.getHeaders().setBearerAuth(token);
////                    }
////                    return execution.execute(request, body);
////                })
//                .build();
//    }



    @GetMapping("/account/add")
    public String addAccount(Model model) {
        UserDTO userDTO = restClient.addAccount();
        log.info("1 --> " + userDTO);
        model.addAttribute("user", userDTO);
        return "UserDto";
    }

    @GetMapping("/account")
    public String getAccount(Model model) {
        List<String> customers = restClient.getAccount();
        log.info("1 --> " + customers);
        model.addAttribute("customers", customers);
        return "account";
    }


    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("category", new Category());
        return "catalog";
    }

    @GetMapping("/shop/addcar")
    public String addCar(Model model) {
        model.addAttribute("item", restClient.addCar());
        return "item";
    }



    @PostMapping("/catalog")
    public String getCatalog(@ModelAttribute("category") Category category, Model model, Principal principal) {
        ListItemDto listItemDto = this.restClient.findItem(category);
        model.addAttribute("items", listItemDto.getItemList());
        return "catalog";
    }

}