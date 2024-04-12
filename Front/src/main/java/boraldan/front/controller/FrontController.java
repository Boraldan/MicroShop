package boraldan.front.controller;


import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.front.client.RestClientProductsRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("category", new Category());
        return "catalog";
    }

    @PostMapping("/catalog")
    public String getCatalog(@ModelAttribute("category") Category category, Model model, Principal principal) {
        System.out.println(principal.toString());
        ListItemDto listItemDto = this.restClient.findItem(category);
        model.addAttribute("items", listItemDto.getItemList());
        return "catalog";
    }

}