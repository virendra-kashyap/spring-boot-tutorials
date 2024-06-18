package com.vaadin.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.vaadin.spring.dto.RoleDTO;

public interface RoleServiceInt {

	public Page<RoleDTO> findAll(int page, int size, String sortDirection);

	public List<RoleDTO> findAll();

	public Optional<RoleDTO> findById(Long id);

	public RoleDTO save(RoleDTO item);

	public void delete(Long id);

	public RoleDTO update(Long id, RoleDTO item);

}
