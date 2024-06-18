package com.vaadin.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.vaadin.spring.dto.UserDTO;

public interface UserServiceInt {
	
	public Page<UserDTO> findAll(int page, int size, String sortDirection);
	
	public List<UserDTO> findAll();
	
	public Optional<UserDTO> findById(Long id);
	
	public UserDTO save(UserDTO item);
	
	public void delete(Long id);
	
	UserDTO update(Long id, UserDTO item);

}
