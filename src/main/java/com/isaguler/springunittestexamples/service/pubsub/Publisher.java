package com.isaguler.springunittestexamples.service.pubsub;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    public void send(String message) {
        for (Subscriber sub : subscribers) {
            try {
                sub.onNext(message);
            } catch (Exception e) {
                //throw new RuntimeException("exception");
            }
        }
    }

    public void sendParallel(String message) {
        subscribers.parallelStream().forEach(sub -> {
            try {
                sub.onNext(message);
            } catch (Exception e) {
                //
            }
        });
    }
}
