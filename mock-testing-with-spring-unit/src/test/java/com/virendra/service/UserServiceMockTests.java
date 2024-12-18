package com.virendra.service;

import com.virendra.entity.UserEntity;
import com.virendra.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceMockTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        // Mock UserEntity object
        UserEntity mockUser = new UserEntity();
        mockUser.setId(2L);
        mockUser.setName("Test User");
        mockUser.setEmailId("test@gmail.com");
        when(userRepository.findById(2L)).thenReturn(Optional.of(mockUser));
        Optional<UserEntity> result = userService.findById(2L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertTrue(result.get().getName().equals("Test User"));
    }

}
