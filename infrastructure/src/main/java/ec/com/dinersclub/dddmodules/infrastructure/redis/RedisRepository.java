package ec.com.dinersclub.dddmodules.infrastructure.redis;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.redis.client.RedisClient;

@ApplicationScoped
public class RedisRepository {

    @Inject
    RedisClient redisClient;

    public void del(String key) {
        redisClient.del(Arrays.asList(key));
    }

    public String get(String key) {
        return redisClient.get(key).toString();
    }

    public void set(String key, String value) {
        redisClient.set(Arrays.asList(key, value));
    }

}