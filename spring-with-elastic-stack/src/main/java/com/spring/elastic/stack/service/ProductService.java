package com.spring.elastic.stack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.elastic.stack.entity.Product;
import com.spring.elastic.stack.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

}
