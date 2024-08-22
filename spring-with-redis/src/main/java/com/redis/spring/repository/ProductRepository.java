package com.redis.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redis.spring.dto.ProductDTO;

public interface ProductRepository extends JpaRepository<ProductDTO, Long> {

}
