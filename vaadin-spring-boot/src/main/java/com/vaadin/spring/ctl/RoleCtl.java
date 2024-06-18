package com.vaadin.spring.ctl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.spring.dto.RoleDTO;
import com.vaadin.spring.service.RoleServiceInt;

@RestController
@RequestMapping("/api/role")
public class RoleCtl {

	@Autowired
	private RoleServiceInt roleServiceInt;

	@GetMapping("list")
	public List<RoleDTO> list() {
		return roleServiceInt.findAll();
	}

	@PostMapping("save")
	public RoleDTO save(@RequestBody RoleDTO role) {
		return roleServiceInt.save(role);
	}

	@PostMapping("update/{id}")
	public RoleDTO update(@PathVariable Long id, @RequestBody RoleDTO role) {
		return roleServiceInt.update(id, role);
	}

	@PostMapping("delete/{id}")
	public void delete(@PathVariable Long id) {
		roleServiceInt.delete(id);
	}

	@GetMapping("get/{id}")
	public Optional<RoleDTO> get(@PathVariable Long id) {
		return roleServiceInt.findById(id);
	}

}
