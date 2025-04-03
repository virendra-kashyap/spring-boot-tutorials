package com.redis.spring.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.redis.spring.dto.User;

@Service
public class UserService {

    private final Map<String, User> database = new HashMap<>();

    @Cacheable(value = "longTermCache", key = "#id")
    public Optional<User> getUserFromLongTermCache(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Cacheable(value = "shortTermCache", key = "#id")
    public Optional<User> getUserFromShortTermCache(String id) {
        return Optional.ofNullable(database.get(id));
    }

    // ✅ Fetch data first, then call caching methods
    @Scheduled(fixedRate = 86400000) // Every 24 hours
    public void scheduleLongTermCacheUpdate() {
        User user = fetchUserFromSource("1"); // Fetch or create data
        if (user != null) {
            addUserToLongTermCache(user);
        }
    }

    @Scheduled(fixedRate = 120000) // Every 2 minutes
    public void scheduleShortTermCacheUpdate() {
        User user = fetchUserFromSource("2"); // Fetch or create data
        if (user != null) {
            addUserToShortTermCache(user);
        }
    }

    // ✅ Cache Methods
    @CachePut(value = "longTermCache", key = "#user.id")
    public User addUserToLongTermCache(User user) {
        database.put(user.getId(), user);
        return user;
    }

    @CachePut(value = "shortTermCache", key = "#user.id")
    public User addUserToShortTermCache(User user) {
        database.put(user.getId(), user);
        return user;
    }

    // ✅ Simulate fetching data (replace with actual DB or API call)
    private User fetchUserFromSource(String id) {
        return new User(id, id.equals("1") ? "John Doe" : "Jane Doe");
    }
}
