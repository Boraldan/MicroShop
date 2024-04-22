package boraldan.front.rest_client.interceptor;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class MainRedisHttpInterceptor implements ClientHttpRequestInterceptor {

    private final HttpSession httpSession;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        if (!request.getHeaders().containsKey("REDIS")) {
            String REDIS_KEY = (String) httpSession.getAttribute("REDIS_KEY");
            request.getHeaders().set("REDIS", REDIS_KEY);
        }
        return execution.execute(request, body);
    }
}
