package com.vaadin.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.vaadin.spring.dto.RoleDTO;
import com.vaadin.spring.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleServiceInt {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Page<RoleDTO> findAll(int page, int size, String sortDirection) {
		return null;
	}

	@Override
	public List<RoleDTO> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<RoleDTO> findById(Long id) {
		return roleRepository.findById(id).map(role -> new RoleDTO(role.getId(), role.getName()));
	}

	@Override
	public RoleDTO save(RoleDTO item) {
		return roleRepository.save(item);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public RoleDTO update(Long id, RoleDTO item) {
		item.setId(id);
		return roleRepository.save(item);
	}

}