package com.isaguler.springunittestexamples.service.pubsub;

public interface Subscriber {
    void onNext(String message);
}
