package com.redis.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redis.spring.dto.EmployeeDTO;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {

}
