package com.vaadin.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vaadin.spring.dto.UserDTO;
import com.vaadin.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceInt {
	
	@Autowired
	private UserRepository userRepository;

//	@Override
//	public Page<UserDTO> findAll(int page, int size, String sortDirection) {
//		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(Sort.Order.asc("firstName"))
//				: Sort.by(Sort.Order.desc("id"));
//		PageRequest pageRequest = PageRequest.of(page, size, sort);
//		return userRepository.findAll(pageRequest);
//	}
	
	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserDTO> findById(Long id) {
		return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmailId()));
	}

	@Override
	public UserDTO save(UserDTO item) {
		return userRepository.save(item);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDTO update(Long id, UserDTO item) {
		item.setId(id);
		return userRepository.save(item);
	}

	@Override
	public Page<UserDTO> findAll(int page, int size, String sortDirection) {
		// TODO Auto-generated method stub
		return null;
	}

}
