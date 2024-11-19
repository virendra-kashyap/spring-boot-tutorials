package com.spring.elastic.stack.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.elastic.stack.entity.AuditElasticDocument;
import com.spring.elastic.stack.repository.AuditTableElasticsearchRepository;

@Service
public class AuditDataService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AuditTableElasticsearchRepository auditElasticRepository;

	private static final int BATCH_SIZE = 1000;

	private AuditElasticDocument mapToElasticDocument(Map<String, Object> row) {
		AuditElasticDocument document = new AuditElasticDocument();
		document.setId(row.get("id").toString());
		document.setEntityName((String) row.get("entity_name"));
		document.setColumnName((String) row.get("column_name"));
		document.setPriorValue((String) row.get("prior_value"));
		document.setNewValue((String) row.get("new_value"));
		document.setUpdatedBy((String) row.get("updated_by"));
		document.setAction((String) row.get("action"));
		document.setTransactionType((String) row.get("transaction_type"));
		document.setUpdatedDate((Date) row.get("updated_date"));
		document.setAnnotation(null); // Handle JSON if needed
		document.setDatatype((String) row.get("datatype"));
		document.setRecordId((Long) row.get("record_id"));
		document.setGroupId((Long) row.get("group_id"));
		document.setEntityId((Long) row.get("entity_id"));
		return document;
	}

	public void migrateData() {
		long lastProcessedId = 0;
		List<Map<String, Object>> postgresData;

		do {
			// Fetch batch of data
			String sql = "SELECT * FROM audit_table_old WHERE id > ? ORDER BY id ASC LIMIT ?";
			postgresData = jdbcTemplate.queryForList(sql, lastProcessedId, BATCH_SIZE);

			// Map rows to Elastic documents
			List<AuditElasticDocument> elasticData = postgresData.stream().map(this::mapToElasticDocument)
					.collect(Collectors.toList());

			// Save to Elasticsearch
			auditElasticRepository.saveAll(elasticData);

			// Update lastProcessedId
			if (!postgresData.isEmpty()) {
				lastProcessedId = ((Number) postgresData.get(postgresData.size() - 1).get("id")).longValue();
				System.out.println("Processed batch. Last ID: " + lastProcessedId);
			}

		} while (!postgresData.isEmpty());
	}

}