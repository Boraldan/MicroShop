package boraldan.shop.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Getter
@AllArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;





}
