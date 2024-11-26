package com.redis.spring.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.benmanes.caffeine.cache.Cache;
import com.redis.spring.dto.EmployeeDTO;
import com.redis.spring.service.EmployeeService;

@RestController
public class CaffeineCacheCtl {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CacheManager cacheManager;

	@GetMapping("/employees")
	public List<EmployeeDTO> getEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/cache/stats")
	public Object getCacheStats() {
		Cache<Object, Object> cache = (Cache<Object, Object>) cacheManager.getCache("employeesCache").getNativeCache();
		return cache.asMap();
	}

}
