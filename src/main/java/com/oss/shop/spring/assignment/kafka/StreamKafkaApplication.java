package com.oss.shop.spring.assignment.kafka;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class StreamKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamKafkaApplication.class, args);
    }

}

@Component
class TestMessaging implements CommandLineRunner {

    @Autowired
    private TestStreamService testStreamService;

    public void run(String... args){
        testStreamService.sendMessage("Hello Kafka");
    }
}
