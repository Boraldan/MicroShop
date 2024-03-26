package boraldan.front.session;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MySessionListener implements HttpSessionListener {

    private final RedisTemplate<String, Object> redis;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // Обработка события создания сессии
        event.getSession().setMaxInactiveInterval(100);
        System.out.println("Session Created: " + event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

       // Обработка события закрытия сессии
        redis.opsForValue().getOperations().delete(event.getSession().getId());
        System.out.println("Session Destroyed: " + event.getSession().getId());
    }
}