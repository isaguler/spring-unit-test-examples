package com.isaguler.springunittestexamples.service.pubsub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PublisherTest {

    private final Publisher publisher = new Publisher();
    private final Subscriber subscriber1 = mock(Subscriber.class);
    private final Subscriber subscriber2 = mock(Subscriber.class);

    @BeforeEach
    void setUp() {
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
    }

    @Test
    void subscribeWithGivenSubscriberArgument() {
    }

    @Test
    void sendMessageToAllSubscribers() {
        publisher.send("Hello");

        verify(subscriber1).onNext("Hello");
        verify(subscriber2).onNext("Hello");
    }

    @Test
    void sendMessageToAllSubscribersThrowsException() {
        doThrow(RuntimeException.class).when(subscriber1).onNext(anyString());

        publisher.send("message 1");
        publisher.send("message 2");

        verify(subscriber1, times(2)).onNext(matches("message \\d"));
        verify(subscriber2, times(2)).onNext(anyString());
    }

    @Test
    void sendParallel() {
        publisher.sendParallel("parallel");

        verify(subscriber1).onNext(matches("parallel"));
        verify(subscriber2).onNext(anyString());
    }
}