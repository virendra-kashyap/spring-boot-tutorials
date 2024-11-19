package com.spring.elastic.stack.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(indexName = "audit_index")
public class AuditElasticDocument {

	@Id
	private String id; // Combine id and entityName if needed
	private String entityName;
	private String columnName;
	private String priorValue;
	private String newValue;
	private String updatedBy;
	private String action;
	private String transactionType;
	private Date updatedDate;
	private Map<String, Object> annotation; // JSON to Map
	private String datatype;
	private Long recordId;
	private Long groupId;
	private Long entityId;
}