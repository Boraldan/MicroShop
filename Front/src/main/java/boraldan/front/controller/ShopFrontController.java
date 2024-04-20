package boraldan.front.controller;


import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.front.rest_client.ShopRestClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ShopFrontController {

    private final ShopRestClient restClient;
    private final HttpSession httpSession;

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


    @GetMapping("/carttest")
    public String test(Model model, @AuthenticationPrincipal Principal principal, @ModelAttribute("cart") Cart cart) {
        return "cart";

    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("category", new Category());
        return "catalog";
    }

    @PostMapping("/catalog")
    public String getCatalog(@ModelAttribute("category") Category category, Model model) {
        ListItemDto listItemDto = this.restClient.findItem(category);
        model.addAttribute("items", listItemDto.getItemList());
        return "catalog";
    }

    @GetMapping("/shop/addcar")
    public String addCar(Model model) {
        model.addAttribute("item", restClient.addCar());
        return "item";
    }

    @GetMapping
    public String index(Model model,
                        Principal principal,
                        @AuthenticationPrincipal Principal principal2,
                        Authentication authentication) {
        System.out.println("index  principal  -->  " + principal);
        System.out.println("index  principal2  -->  " + principal2);
        System.out.println("index  authentication  -->  " + authentication);
        System.out.println("index  httpSession  -->  " + httpSession.getId());
        return "index";
    }

}