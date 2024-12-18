package com.virendra.service;

import com.virendra.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceUnitTests {

    @Autowired
    UserService userService;

    @Test
    void testFindById() {
        assertNotNull(userService.findById(2));
    }

    @Test
    void testListSize() {
        assertEquals(2, userService.list().size());
    }

}
