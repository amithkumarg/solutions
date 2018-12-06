package com.oss.shop.spring.assignment.kafka;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TestStreams {
    String OUTPUT = "test-out";

    @Output(OUTPUT)
    MessageChannel outbound();
}