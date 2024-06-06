package boraldan.front.config;

import boraldan.front.rest_client.*;
import boraldan.front.rest_client.interceptor.MainRedisHttpInterceptor;
import boraldan.front.rest_client.interceptor.OAuth2HttpInterceptor;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientBean {

    @Bean
    public AccountRestClient accountRestClient(
            @Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${microshop.service.shop.registration-id:keycloak}") String registrationId,
            HttpSession httpSession) {
        return new AccountRestClient(RestClient.builder()
                .baseUrl(gatewayBaseUri)
                .requestInterceptor(
                        new OAuth2HttpInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                                        authorizedClientRepository), registrationId, httpSession))
                .build());
    }

    @Bean
    public AuthorizeRestClient authorizeRestClient(
            @Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri,
            HttpSession httpSession) {
        return new AuthorizeRestClient(RestClient.builder()
                .baseUrl(gatewayBaseUri)
                .requestInterceptor(new MainRedisHttpInterceptor(httpSession))
                .build());
    }

    @Bean
    public CartRestClient cartRestClient(@Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri) {
        return new CartRestClient(RestClient.builder()
                .baseUrl(gatewayBaseUri)
                .build());
    }

    @Bean
    public ShopRestClient shopRestClient(
            @Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri,
            HttpSession httpSession) {
        return new ShopRestClient(RestClient.builder()
                .baseUrl(gatewayBaseUri)
                .requestInterceptor(new MainRedisHttpInterceptor(httpSession))
                .build());
    }

    @Bean
    public StorageRestClient storageRestClient(@Value("${microshop.service.shop.uri:http://localhost:8765}") String gatewayBaseUri) {
        return new StorageRestClient(RestClient.builder()
                .baseUrl(gatewayBaseUri)
                .build());
    }

}
