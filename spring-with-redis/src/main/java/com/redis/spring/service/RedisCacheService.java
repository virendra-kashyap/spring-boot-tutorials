//package com.redis.spring.service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.redis.spring.dto.EmployeeDTO;
//
//import jakarta.annotation.PostConstruct;
//
//@Service
//public class RedisCacheService {
//	
//	@Autowired
//	private RestTemplate restTemplate;
//
//	@Autowired
//	private RedisTemplate<String, List<EmployeeDTO>> redisTemplate;
//	
//	private static final String CACHE_KEY = "recent_data";
//	
//	@PostConstruct
//	public void initializeCache() {
//		putDataInCache();
//	}
//
//	@Scheduled(fixedRate = 1800000)
//	public void fetchDataAndCache() {
//		putDataInCache();
//	}
//	
//	private void putDataInCache() {
//		ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(
//			    "http://localhost:8182/api/employee/list",
//			    HttpMethod.GET,
//			    null,
//			    new ParameterizedTypeReference<List<EmployeeDTO>>() {}
//			);
//
//			List<EmployeeDTO> responseData = response.getBody();
//			if (responseData != null) {
//			    List<EmployeeDTO> sixMonthData = responseData.stream()
//			            .filter(e -> e.getEndDate().isAfter(LocalDate.now().minusMonths(6)))
//			            .collect(Collectors.toList());
//			    redisTemplate.opsForValue().set(CACHE_KEY, sixMonthData);
//			}
//	}
//}
