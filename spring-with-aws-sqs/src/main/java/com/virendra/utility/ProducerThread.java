package com.virendra.utility;

import lombok.NoArgsConstructor;

public class ProducerThread extends Thread {

    public ProducerThread() {
        try {
            System.out.println("Thread started: " + Thread.currentThread().getName());
            Thread.sleep(120000);
            System.out.println("Thread execution completed: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
