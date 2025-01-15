package com.virendra.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service1/")
public class HelloCtl {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("hello")
    public String test() {
        return restTemplate.getForObject("http://localhost:8082/service2/hello", String.class);
    }

    @GetMapping("mock")
    public String mock() {
        return restTemplate.getForObject("http://localhost:8081/hello", String.class);
    }

}
