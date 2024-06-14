package com.vaadin.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.spring.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

}