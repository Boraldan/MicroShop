package boraldan.gateway.service;

//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.security.Principal;
//
//@Service
//@AllArgsConstructor
//public class HeaderService {
//
//    private OAuth2AuthorizedClientService authorizedClientService;
//
//    public HttpHeaders addAuthHeader(Principal principal){
//        // Получение access токена
//        String accessToken = authorizedClientService
//                .loadAuthorizedClient("reg-client", principal.getName())
//                .getAccessToken().getTokenValue();
//        // Создаем заголовок и помещаем в него токен авторизации
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Authorization", "Bearer " + accessToken);
//        return httpHeaders;
//    }
//
//}
