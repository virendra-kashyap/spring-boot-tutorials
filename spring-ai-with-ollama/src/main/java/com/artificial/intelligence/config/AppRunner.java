package com.artificial.intelligence.config;

import com.artificial.intelligence.service.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public AppRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        GreetingService greetingService = applicationContext.getBean(GreetingService.class);
        System.out.println("greetingService @@ " + greetingService.getGreeting());
    }
}
