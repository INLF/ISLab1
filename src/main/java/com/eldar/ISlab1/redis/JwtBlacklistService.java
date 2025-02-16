package com.eldar.ISlab1.redis;

import com.eldar.ISlab1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
/**
 If our token in Redis storage => It's blacklisted
 we will set ttl based on given time in milliseconds (to match jwt exp - iat)
 we dont need jwt to be blacklisted if it is expired
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtBlacklistService {
    private final RedisTemplate<String, String> redisTemplate;


    public void blacklistToken(String token) {
        long expirationMillis = JwtUtil.timeToExpire(token, ChronoUnit.MILLIS);
        String jti = JwtUtil.extractId(token);
        if (expirationMillis > 0) {
            redisTemplate.opsForValue().setIfAbsent(jti, "", expirationMillis, TimeUnit.MILLISECONDS);
            log.info("Blacklisting token with jti: {}\n time until expiration {}",jti ,expirationMillis);
        } else {
            log.info("Token with jti: {} already expired!", jti);
        }
    }


    public boolean isTokenBlacklisted(String token) {
        String jti = JwtUtil.extractId(token);
        return Boolean.TRUE.equals(redisTemplate.hasKey(jti));
    }


    public long getTokenTTL(String token) {
        return redisTemplate.getExpire(token, TimeUnit.MILLISECONDS);
    }
}