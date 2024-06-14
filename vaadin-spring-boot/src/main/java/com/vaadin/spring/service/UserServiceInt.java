package com.vaadin.spring.service;

import java.util.List;
import java.util.Optional;

import com.vaadin.spring.dto.UserDTO;

public interface UserServiceInt {
	
	public List<UserDTO> findAll();
	
	public Optional<UserDTO> findById(Long id);
	
	public UserDTO save(UserDTO item);
	
	public void delete(Long id);
	
	UserDTO update(Long id, UserDTO item);

}
