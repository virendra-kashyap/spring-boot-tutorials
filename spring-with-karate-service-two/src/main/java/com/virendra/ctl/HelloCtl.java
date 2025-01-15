package com.virendra.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service2/")
public class HelloCtl {

    @GetMapping("hello")
    public String hello() {
        return "Hello Service 2";
    }
}
