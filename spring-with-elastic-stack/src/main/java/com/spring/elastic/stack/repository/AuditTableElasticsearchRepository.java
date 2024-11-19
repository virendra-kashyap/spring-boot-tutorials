package com.spring.elastic.stack.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.elastic.stack.entity.AuditElasticDocument;

public interface AuditTableElasticsearchRepository extends ElasticsearchRepository<AuditElasticDocument, String> {

}