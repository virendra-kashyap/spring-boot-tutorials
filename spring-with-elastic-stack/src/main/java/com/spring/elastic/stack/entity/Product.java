package com.spring.elastic.stack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "products")
public class Product {

	@Id
	private String id;
	private String name;
	private String description;
	private double price;

}
