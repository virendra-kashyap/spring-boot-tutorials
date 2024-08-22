package com.redis.spring.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.spring.dto.ProductDTO;
import com.redis.spring.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Cacheable(value = "productCache", key = "#id")
	public ProductDTO getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll();
	}

	@CacheEvict(value = "productCache", key = "#product.id")
	public ProductDTO saveProduct(ProductDTO product) {
		return productRepository.save(product);
	}

	@CacheEvict(value = "productCache", key = "#id")
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
