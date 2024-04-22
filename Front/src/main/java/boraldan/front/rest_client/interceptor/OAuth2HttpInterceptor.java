package boraldan.front.rest_client.interceptor;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

/**
 * Класс ловит исходящий запрос. Если в заголовке нет JWT токкена, то отправляет на сервер для аутентификации
 * и после внедряет токкен в хеддер.
 */


@RequiredArgsConstructor
public class OAuth2HttpInterceptor implements ClientHttpRequestInterceptor {

    @Setter
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final String registrationId;
    private final HttpSession httpSession;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager
                    .authorize(OAuth2AuthorizeRequest.withClientRegistrationId(this.registrationId)
                    .principal(this.securityContextHolderStrategy.getContext().getAuthentication())
                    .build());
            request.getHeaders().setBearerAuth(authorizedClient.getAccessToken().getTokenValue());
        }

        if (!request.getHeaders().containsKey("REDIS")) {
            String REDIS_KEY = (String) httpSession.getAttribute("REDIS_KEY");
            request.getHeaders().set("REDIS", REDIS_KEY);
        }

        return execution.execute(request, body);
    }
}
