package com.virendra.service;

import com.virendra.entity.UserEntity;
import com.virendra.exception.EntityNotFoundException;
import com.virendra.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserEntity> list() {
       return userRepository.findAll();
    }

    public Optional<UserEntity> findById(long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
        return user;
    }
}
