package com.example.service;

import java.net.SocketException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.dto.Alarm;
import com.example.dto.AlarmChangeDetails;
import com.example.dto.AlarmResponseData;

import reactor.util.retry.Retry;

@Service
public class AlarmFeedAsyncService {

	private final Map<String, AlarmChangeDetails> deviceApiCalledMap = new ConcurrentHashMap<>();
	private final WebClient webClient = WebClient.builder().build();

	public void processData(Alarm alarm) {
		changeManagement(alarm.getDeviceName(), alarm.getSiteId(), alarm.getId());
	}

	private void changeManagement(String deviceId, String siteId, Long alarmId) {

		if (!deviceApiCalledMap.containsKey(deviceId)) {
			AlarmChangeDetails details = new AlarmChangeDetails();
			details.setIsCalled(false);
			details.setDeviceId(deviceId);
			deviceApiCalledMap.put(deviceId, details);
		}
		
		System.out.println("deviceApiCalledMap ## " + deviceApiCalledMap);

		if (deviceApiCalledMap.containsKey(deviceId)) {
			AlarmChangeDetails details = deviceApiCalledMap.get(deviceId);
			if (!details.getIsCalled()) {

				List<AlarmResponseData> response = webClient.get().uri(
						"http://localhost:8080/snow/tracy/changeRequestForDeviceId?deviceId={deviceId}&siteId={siteId}",
						deviceId, siteId).retrieve()
						.bodyToMono(new ParameterizedTypeReference<List<AlarmResponseData>>() {
						}).doOnError(error -> System.out.println("Exception " + error.getMessage())).block();

				System.out.println("API Response: " + response);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				for (AlarmResponseData alarmResponse : response) {
					String newStartDateStr = "2025-03-12 11:15:01";
					String newEndDateStr = "2025-03-12 11:30:01";

					LocalDateTime newStartDate = LocalDateTime.parse(newStartDateStr, formatter);
					LocalDateTime newEndDate = LocalDateTime.parse(newEndDateStr, formatter);

					alarmResponse.setStartDate(newStartDate);
					alarmResponse.setEndDate(newEndDate);

					System.out.println("Updated AlarmResponseData: " + alarmResponse);
				}
				
				for (AlarmResponseData data : response) {
					LocalDateTime currentDateTime = LocalDateTime.now();
					LocalDateTime startDateTime = data.getStartDate();
					LocalDateTime endDateTime = data.getEndDate();
					if (currentDateTime.isAfter(startDateTime) && currentDateTime.isBefore(endDateTime)) {
						details.setIsCalled(true);
						details.setStartDate(data.getStartDate());
						details.setEndDate(data.getEndDate());
						details.setNumber(data.getNumber());
						deviceApiCalledMap.put(deviceId, details);
						Map<String, Object> alarmPatchData = Map.of(Alarm.Fields.incidentRef,
								data.getNumber() + " @ Suppressed ");
						updateAlarmIncidentDetails(alarmId, alarmPatchData);
					}
				}
			}
		}

		if (deviceApiCalledMap.containsKey(deviceId)) {
			AlarmChangeDetails details = deviceApiCalledMap.get(deviceId);

			if (details.getStartDate() == null || details.getEndDate() == null) {
				deviceApiCalledMap.remove(deviceId);
				System.out.println("Device ID {} has null startDate or endDate" + deviceId);
			}
		}

		if (deviceApiCalledMap.containsKey(deviceId)) {
			AlarmChangeDetails details = deviceApiCalledMap.get(deviceId);
			LocalDateTime endDateTime = details.getEndDate();

			if (endDateTime.isBefore(LocalDateTime.now())) {
				deviceApiCalledMap.remove(deviceId);
				Map<String, Object> alarmPatchData = Map.of(Alarm.Fields.incidentRef, "");
				updateAlarmIncidentDetails(alarmId, alarmPatchData);
			}
		}
	}

	private void updateAlarmIncidentDetails(Long alarmId, Map<String, Object> alarmPatchData) {

		System.out.println("Update in postgres ");

		URI endpointUri = UriComponentsBuilder.fromUriString("http://localhost:8087/api/v1/alarm").pathSegment(String.valueOf(alarmId)).build().toUri();

		webClient.patch().uri(endpointUri).bodyValue(alarmPatchData).retrieve().bodyToMono(String.class)
				.doOnSuccess(response -> {
					System.out.println(
							"updateAlarmIncidentDetails service call was successful for alarmId: {} : alarmPatchData : {}"
									+ alarmId + " " + alarmPatchData);
				}).doOnError(error -> {
					System.out.println(
							"updateAlarmIncidentDetails service call failed for alarmId: {} : alarmPatchData : {} : {}"
									+ alarmId + " " + alarmPatchData + " " + error.getMessage());
				}).retryWhen(Retry.backoff(3, Duration.ofSeconds(5))
						.filter(throwable -> throwable instanceof SocketException));
	}
}
