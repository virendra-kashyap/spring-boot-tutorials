package com.redis.spring.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.spring.dto.ProductDTO;
import com.redis.spring.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductCtl {

	private final ProductService productService;

	public ProductCtl(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO product) {
		return productService.saveProduct(product);
	}

	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
		product.setId(id);
		return productService.saveProduct(product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}

}