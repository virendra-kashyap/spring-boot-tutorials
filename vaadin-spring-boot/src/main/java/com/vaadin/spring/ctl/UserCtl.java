package com.vaadin.spring.ctl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.spring.dto.UserDTO;
import com.vaadin.spring.service.UserServiceInt;

@RestController
@RequestMapping("/api/users/")
public class UserCtl {
	
	@Autowired
	private UserServiceInt userServiceInt;
	
//	@GetMapping("list")
//	public Page<UserDTO> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
//			@RequestParam(defaultValue = "asc") String sortDirection) {
//		return userServiceInt.findAll(page, size, sortDirection);
//	}
	
	@GetMapping("list")
    public List<UserDTO> list() {
        return userServiceInt.findAll();
    }
	
	@PostMapping("save")
    public UserDTO save(@RequestBody UserDTO user) {
        return userServiceInt.save(user);
    }
	
	@PostMapping("update/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO user) {
        return userServiceInt.update(id, user);
    }

	@PostMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
		userServiceInt.delete(id);
    }
	
	@GetMapping("get/{id}")
    public Optional<UserDTO> get(@PathVariable Long id) {
        return userServiceInt.findById(id);
    }

}