package com.redis.spring.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.spring.service.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisCtl {

	@Autowired
	private RedisService redisService;

	@PostMapping("/save")
	public String save(@RequestParam String key, @RequestParam String value) {
		redisService.saveValue(key, value);
		return "Value saved";
	}

	@GetMapping("/get")
	public Object get(@RequestParam String key) {
		return redisService.getValue(key);
	}

}
