package com.vulnerabilities.spring.controller;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vulnerabilities.spring.model.User;
import com.vulnerabilities.spring.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/users")
	public ResponseEntity<User> add(@Valid @RequestBody User user) {
		final var userDb = userRepository.save(user);
		final var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userDb.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> delete(@PathVariable String id) {
		final var userDb = userRepository.findById(Integer.parseInt(id))
				.orElseThrow(() -> new RuntimeException(String.format("User with ID as %s not found", id)));
		userRepository.delete(userDb);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody User user) {
		final var userDb = userRepository.findById(Integer.parseInt(id))
				.orElseThrow(() -> new RuntimeException(String.format("User with ID as %s not found", id)));
		user.setId(userDb.getId());
		userRepository.save(user);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> search() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<EntityModel<User>> getById(@PathVariable String id) {
		final var userDb = userRepository.findById(Integer.parseInt(id))
				.orElseThrow(() -> new RuntimeException(String.format("User with ID as %s not found", id)));
		final EntityModel<User> model = EntityModel.of(userDb);
		final WebMvcLinkBuilder newLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).search());
		final Link link = newLink.withRel("Additional User info Link");
		model.add(link);
		return ResponseEntity.ok(model);
	}

}