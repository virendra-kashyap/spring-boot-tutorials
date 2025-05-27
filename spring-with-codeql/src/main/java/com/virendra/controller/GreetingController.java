package com.virendra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", defaultValue = "World") String name) {
        String safeName = HtmlUtils.htmlEscape(name);
        return "Hello, " + safeName + "!";
    }

}
