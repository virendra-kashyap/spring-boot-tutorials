package com.redis.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.redis.spring.dto.RoleDTO;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, RoleDTO> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, RoleDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // JSON Serializer for RoleDTO
        Jackson2JsonRedisSerializer<RoleDTO> serializer = new Jackson2JsonRedisSerializer<>(RoleDTO.class);
        template.setDefaultSerializer(serializer);

        return template;
    }

    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory
    // redisConnectionFactory) {
    // RedisTemplate<String, Object> template = new RedisTemplate<>();
    // template.setConnectionFactory(redisConnectionFactory);

    // // Use String serializer for keys
    // template.setKeySerializer(new StringRedisSerializer());

    // // Use Jackson JSON serializer for values
    // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    // template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    // return template;
    // }

    // @Bean
    // RedisTemplate<String, RoleDTO> redisTemplate(RedisConnectionFactory
    // redisConnectionFactory) {
    // RedisTemplate<String, RoleDTO> redisTemplate = new RedisTemplate<>();
    // redisTemplate.setConnectionFactory(redisConnectionFactory);

    // // Set serializers for keys and values
    // redisTemplate.setKeySerializer(new StringRedisSerializer());
    // redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

    // return redisTemplate;
    // }

    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory
    // redisConnectionFactory) {
    // RedisTemplate<String, Object> template = new RedisTemplate<>();
    // template.setConnectionFactory(redisConnectionFactory);
    // template.setKeySerializer(new StringRedisSerializer());
    // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    // return template;
    // }

    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory
    // connectionFactory) {
    // RedisTemplate<String, Object> template = new RedisTemplate<>();
    // template.setConnectionFactory(connectionFactory);

    // template.setKeySerializer(new StringRedisSerializer());
    // template.setHashKeySerializer(new StringRedisSerializer());

    // GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
    // GenericJackson2JsonRedisSerializer();
    // template.setValueSerializer(jackson2JsonRedisSerializer);
    // template.setHashValueSerializer(jackson2JsonRedisSerializer);

    // template.afterPropertiesSet();
    // return template;
    // }

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
