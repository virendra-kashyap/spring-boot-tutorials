package com.spring.elastic.stack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.elastic.stack.entity.Product;
import com.spring.elastic.stack.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@GetMapping
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{name}")
	public List<Product> searchByName(@PathVariable String name) {
		return productService.findByName(name);
	}

}
