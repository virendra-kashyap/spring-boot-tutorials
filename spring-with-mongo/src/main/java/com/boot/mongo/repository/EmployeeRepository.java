package com.boot.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boot.mongo.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
