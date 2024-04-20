package boraldan.front.config_oauth2;

//@Configuration
//public class AccountRestClientBean {

//    @Bean
//    public AccountRestClient accountRestClient(
//            @Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri,
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientRepository authorizedClientRepository,
//            @Value("${microshop.service.shop.registration-id:keycloak}") String registrationId
//    ) {
//        return new AccountRestClient(RestClient.builder()
//                .baseUrl(gatewayBaseUri)
//                .requestInterceptor(
//                        new OAuth2HttpInterceptor(
//                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
//                                        authorizedClientRepository), registrationId))
//                .build());
//    }
//}
