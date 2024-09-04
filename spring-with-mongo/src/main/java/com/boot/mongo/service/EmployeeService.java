package com.boot.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boot.mongo.entity.Employee;
import com.boot.mongo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> updateEmployee(String id, Employee employee) {
		if (!employeeRepository.existsById(id)) {
			return Optional.empty();
		}

		employee.setId(id);
		return Optional.of(employeeRepository.save(employee));
	}

	public void deleteEmployee(String id) {
		employeeRepository.deleteById(id);
	}

}
