package com.redis.spring.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfig {

    // @Bean
    // LettuceConnectionFactory redisConnectionFactory() {
    // RedisStandaloneConfiguration configuration = new
    // RedisStandaloneConfiguration("localhost", 6379);
    // return new LettuceConnectionFactory(configuration);
    // }

    // @Bean
    // RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory
    // connectionFactory) {
    // RedisTemplate<String, Object> template = new RedisTemplate<>();
    // template.setConnectionFactory(connectionFactory);

    // // Set up proper key and value serializers
    // template.setKeySerializer(new StringRedisSerializer());
    // template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    // return template;
    // }

//     @Bean
//     public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//         return builder -> builder
//                 .withCacheConfiguration("longTermCache",
//                         RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(365)))
//                 .withCacheConfiguration("shortTermCache",
//                         RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)));
//     }

}
