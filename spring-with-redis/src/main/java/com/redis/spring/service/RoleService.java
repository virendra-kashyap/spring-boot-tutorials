package com.redis.spring.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.spring.dto.RoleDTO;

@Service
public class RoleService {

    @Autowired
    private RedisTemplate<String, RoleDTO> redisTemplate;

    public RoleService(RedisTemplate<String, RoleDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // @CachePut(value = "ROLE_CACHE", key = "#roleDTO.id")
    public RoleDTO save(RoleDTO roleDTO) {
        redisTemplate.opsForValue().set("cdt::ROLE_CACHE::" + roleDTO.getId(), roleDTO);
        return roleDTO;
    }

    public List<RoleDTO> findAllFromCache() {
        Set<String> keys = redisTemplate.keys("cdt::ROLE_CACHE::*");
        if (keys == null || keys.isEmpty()) {
            return List.of();
        }
    
        return keys.stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .filter(obj -> obj instanceof RoleDTO)
                .map(obj -> (RoleDTO) obj)
                .collect(Collectors.toList());
    }
    
}
