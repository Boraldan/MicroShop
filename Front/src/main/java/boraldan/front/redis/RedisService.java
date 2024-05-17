package boraldan.front.redis;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Getter
@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, CartDto> redisTemplate;
    private ValueOperations<String, CartDto> opsForValue;

    @PostConstruct
    private void init() {
        this.opsForValue = redisTemplate.opsForValue();
    }

    public void setCart(String cartKey, CartDto cart){
        opsForValue.set(cartKey, cart);
    }

    public CartDto getCart(String cartKey){
        return opsForValue.get(cartKey);
    }

    public void addItemToCart(String cartKey, CartUnitDto unitCart) {
        CartDto cart = opsForValue.get(cartKey);
        if (cart == null) {
            cart = new CartDto();
        }
        cart.getCartUnitDtoList().add(unitCart);
        opsForValue.set(cartKey, cart);
    }

    public void deleteCart(String cartKey) {
        redisTemplate.delete(cartKey);
    }

    public void redisClean() {
        RedisClient client = RedisClient.create("redis://localhost");
        try (client) {
            // Подключение к Redis
            try (StatefulRedisConnection<String, String> connection = client.connect()) {
                RedisCommands<String, String> commands = connection.sync();
                // Очистка всех данных из текущей базы данных
                commands.flushdb();
                System.out.println("Данные из Redis успешно очищены.");
            }
        }
    }
}
