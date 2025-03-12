package com.example.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.utility.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmResponseData {

	private String deviceId;

	@JsonProperty("assignment_group")
	private String assignmentGroup;

	@JsonProperty("cmdb_ci")
	private String cmdbCi;

	private String description;

	@JsonProperty("short_description")
	private String shortDescription;

	private String number;
	private String risk;
	private String state;

	@JsonProperty("siteId")
	private String siteId;

	@JsonProperty("radiance_cmdb_ci")
	private List<String> radianceCmdbCi;

	@JsonProperty("u_change_request_urgent")
	private String changeRequestUrgent;

	// Custom DateTimeFormatter to parse the date string in the format 'yyyy-MM-dd
	// HH:mm:ss'
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@JsonProperty("start_date")
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private LocalDateTime startDate;

	@JsonProperty("end_date")
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private LocalDateTime endDate;

	@JsonProperty("sys_created_on")
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private LocalDateTime sysCreatedOn;

}
