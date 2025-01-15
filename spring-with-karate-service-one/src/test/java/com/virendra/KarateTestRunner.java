package com.virendra;

import com.intuit.karate.junit5.Karate;

public class KarateTestRunner {

    @Karate.Test
    Karate testService() {
        return Karate.run("classpath:mock-service.feature").relativeTo(getClass());
    }

}
