package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 给key设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setExp(String key, String value, Long time) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        Boolean expire = redisTemplate.expire(key, time, TimeUnit.SECONDS);
        if (expire) {
            return true;
        }
        return false;

    }

    /**
     * 判读key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public boolean remove(String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }

    /**
     * 校验token
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                return false;
            }
        }

        if (!exists(token)) {
            return false;
        }

        if (!remove(token)) {
            return false;
        }
        return true;
    }
}
