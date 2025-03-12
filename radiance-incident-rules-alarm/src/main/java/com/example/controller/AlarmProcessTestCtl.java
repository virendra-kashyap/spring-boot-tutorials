package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Alarm;
import com.example.service.AlarmFeedAsyncService;

@RestController
@RequestMapping("/api/v1/")
public class AlarmProcessTestCtl {
	
	@Autowired
	AlarmFeedAsyncService alarmFeedAsyncService;
	
	@PostMapping("process")
	public String processDate(@RequestBody Alarm alarm) {
		System.out.println("Call Controller ");
		alarmFeedAsyncService.processData(alarm);
		return "Successfully process";
	}

}
