package com.template.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.template.model.User;

public interface UserRepository extends ListCrudRepository<User, Long> {

}
