package com.example.dto;

import lombok.Data;

@Data
public class Alarm {

	private Long id;
	private String deviceName;
	private String siteId;

	public static class Fields {
		public static final String incidentRef = "incidentRef";
	}

}
