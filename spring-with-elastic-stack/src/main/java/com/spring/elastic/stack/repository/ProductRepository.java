package com.spring.elastic.stack.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.elastic.stack.entity.Product;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

	List<Product> findByName(String name);

}