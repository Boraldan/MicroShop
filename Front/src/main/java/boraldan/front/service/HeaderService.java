package boraldan.front.service;

//@Service
//@AllArgsConstructor
//public class HeaderService {
//
//    private OAuth2AuthorizedClientService authorizedClientService;
//
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
//
//}
