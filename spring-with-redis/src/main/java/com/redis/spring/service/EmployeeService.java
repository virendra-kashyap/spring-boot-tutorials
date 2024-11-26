package com.redis.spring.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.spring.dto.EmployeeDTO;
import com.redis.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@jakarta.annotation.PostConstruct
	public void init() {
		getEmployees();
		getEmployees();
	}

	@Cacheable("employeesCache") 
	public List<EmployeeDTO> getEmployees() {
		List<EmployeeDTO> employees = employeeRepository.findAll();
		return employees;
	}
}
