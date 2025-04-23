package com.artificial.intelligence.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting() {
        return "Hey bro! Greeting from GreetingService";
    }

}
