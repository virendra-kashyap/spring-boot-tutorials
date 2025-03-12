package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AlarmChangeDetails {
	
	private Boolean isCalled;    
	private String deviceId;    
	private LocalDateTime startDate;    
	private LocalDateTime endDate;
	private String number;

}
