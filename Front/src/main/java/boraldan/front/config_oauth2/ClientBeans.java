package boraldan.front.config_oauth2;

import boraldan.front.client.RestClientProductsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    // создаем RestClient с автоматическим внедрением jwt в хеддер, если его там нет
//    @Bean
//    public RestClientProductsRestClient productsRestClient(
//            @Value("${microshop.service.shop.uri:http://localhost:8080}") String catalogueBaseUri,
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientRepository authorizedClientRepository,
//            @Value("${microshop.service.shop.registration-id:keycloak}") String registrationId) {
//        return new RestClientProductsRestClient(RestClient.builder()
//                .baseUrl(catalogueBaseUri)
//                .requestInterceptor(
//                        new OAuthClientHttpRequestInterceptor(
//                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
//                                        authorizedClientRepository), registrationId))
//                .build());
//    }


    //  исключили автоматическое внедрение токкена jwt в исходящий запрос в хеддер
    @Bean
    public RestClientProductsRestClient productsRestClient(
            @Value("${microshop.service.shop.uri:http://localhost:8080}") String catalogueBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${microshop.service.shop.registration-id:keycloak}") String registrationId) {
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
//                .requestInterceptor(
//                        new OAuthClientHttpRequestInterceptor(
//                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
//                                        authorizedClientRepository), registrationId))
                .build());
    }
}


