package mono.ecommerce.security.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final long tokenValidityMilliseconds;

    public RedisService(RedisTemplate<String, Object> redisTemplate, @Value("${jwt.tokenValidityMilliseconds}") long tokenValidityMilliseconds) {
        this.redisTemplate = redisTemplate;
        this.tokenValidityMilliseconds = tokenValidityMilliseconds;
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void addLogoutList(String token) {
        redisTemplate.opsForValue().set("LOGOUT_" + token, "LOGOUT", tokenValidityMilliseconds, TimeUnit.MILLISECONDS);
    }
}
