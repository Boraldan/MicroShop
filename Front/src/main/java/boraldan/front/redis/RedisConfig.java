package boraldan.front.redis;

import boraldan.entitymicro.cart.dto.CartDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }


//    @Bean
//    public RedisTemplate<String, CartDto> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, CartDto> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return template;
//    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public RedisTemplate<String, CartDto> redisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {
        RedisTemplate<String, CartDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Настройка сериализатора для ключей
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // Настройка сериализатора для значений по умолчанию
        template.setDefaultSerializer(stringSerializer);

        // Настройка сериализатора для CartDto
        Jackson2JsonRedisSerializer<CartDto> cartDtoSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, CartDto.class);

        // Настройка сериализатора для значений CartDto
        template.setValueSerializer(cartDtoSerializer);
        template.setHashValueSerializer(cartDtoSerializer);

        template.afterPropertiesSet();
        return template;
    }





    //  другой вариант внедрения бинов
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName("localhost");
//        config.setPort(6379);
//        return new LettuceConnectionFactory(config);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;

//    }

}