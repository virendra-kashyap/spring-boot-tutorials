package com.redis.spring.ctl;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.spring.dto.User;
import com.redis.spring.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/long/{id}")
    public Optional<User> getLongTermUser(@PathVariable String id) {
        return userService.getUserFromLongTermCache(id);
    }

    @GetMapping("/short/{id}")
    public Optional<User> getShortTermUser(@PathVariable String id) {
        return userService.getUserFromShortTermCache(id);
    }
    
}
