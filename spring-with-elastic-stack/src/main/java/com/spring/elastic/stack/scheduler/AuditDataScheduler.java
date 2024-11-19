package com.spring.elastic.stack.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.elastic.stack.service.AuditDataService;

@Component
public class AuditDataScheduler {

	@Autowired
	private AuditDataService auditDataService;

	@Scheduled(fixedRate = 120000)
	public void syncData() {
		 System.out.println("Scheduled task is running...");
		auditDataService.migrateData();
	}
}