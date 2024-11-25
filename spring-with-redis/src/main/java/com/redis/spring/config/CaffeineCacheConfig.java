package com.redis.spring.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

	@Bean
	public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("employeesCache"); 
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

	private Caffeine<Object, Object> caffeineCacheBuilder() {
		return Caffeine.newBuilder().initialCapacity(100).maximumSize(500).expireAfterWrite(Duration.ofSeconds(120));
	}
}