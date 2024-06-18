package com.vaadin.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.spring.dto.RoleDTO;

public interface RoleRepository extends JpaRepository<RoleDTO, Long> {

}
