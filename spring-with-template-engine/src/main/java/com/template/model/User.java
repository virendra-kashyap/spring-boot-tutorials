package com.template.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("users")
public class User {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String country;
	private String streetAddress;
	private String city;
	private String region;
	private String postalCode;
}
