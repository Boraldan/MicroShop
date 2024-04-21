package boraldan.front.config_oauth2.interceptor;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class MainRedisHttpInterceptor implements ClientHttpRequestInterceptor {

    //    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    private final HttpSession httpSession;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

//        String username = "";
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("MainRedisHttpInterceptor   authentication  -->  " + authentication);
//
//        if (authentication instanceof OAuth2AuthenticationToken) {
//            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//
//
//            OAuth2User oauth2User = oauthToken.getPrincipal();
//              username = oauth2User.getName();
//            System.out.println(username);
//        }
//
//        if (!request.getHeaders().containsKey("REDIS")) {
//            if (!username.isBlank()) {
//                request.getHeaders().set("REDIS", username);
//
//                System.out.println("MainRedisHttpInterceptor   principal  -->  " + username);
//
//            } else {
//                request.getHeaders().set("REDIS", httpSession.getId());
//
//                System.out.println("MainRedisHttpInterceptor  httpSession -->  " + httpSession.getId());
//            }
//        }
//

        if (!request.getHeaders().containsKey("REDIS")) {
            String REDIS_KEY = (String) httpSession.getAttribute("REDIS_KEY");
            request.getHeaders().set("REDIS", REDIS_KEY);
        }

        return execution.execute(request, body);
    }
}
