package com.oss.shop.spring.assignment.kafka;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class TestStreamService {
    private final TestStreams testsStreams;
    public TestStreamService(TestStreams testsStreams) {
        this.testsStreams = testsStreams;
    }
    public void sendMessage(final String msg) {
        MessageChannel messageChannel = testsStreams.outbound();
        messageChannel.send(MessageBuilder
                .withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
