package com.authorization.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorization.jwt.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

	Optional<UserDTO> findByUsername(String username);

}
