//package com.batch.process.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import com.batch.process.config.BatchConfiguration;
//
//public class ScheduledTasks {
//
//	@Autowired
//	private BatchConfiguration batchConfiguration;
//
//	@Scheduled(fixedRate = 120000) // Run every 2 minutes (120000 milliseconds)
//	public void runBatchJob() {
//		try {
//			batchConfiguration.runJob();
//		} catch (Exception e) {
//		}
//	}
//
//}
