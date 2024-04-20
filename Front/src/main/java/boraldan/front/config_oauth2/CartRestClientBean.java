package boraldan.front.config_oauth2;

//@Configuration
//public class CartRestClientBean {
//
//    @Bean
//    public CartRestClient cartRestClient(
//            @Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri
////            ClientRegistrationRepository clientRegistrationRepository,
////            OAuth2AuthorizedClientRepository authorizedClientRepository,
////            @Value("${microshop.service.shop.registration-id:keycloak}") String registrationId
//    ) {
//        return new CartRestClient(RestClient.builder()
//                .baseUrl(gatewayBaseUri)
//                // TODO: 14.04.2024  здесь код по внедрению токкена jwt в исходящий запрос в хедер
////                .requestInterceptor(
////                        new MyOAuthClientHttpRequestInterceptor(
////                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
////                                        authorizedClientRepository), registrationId))
//                .build());
//    }
//}
