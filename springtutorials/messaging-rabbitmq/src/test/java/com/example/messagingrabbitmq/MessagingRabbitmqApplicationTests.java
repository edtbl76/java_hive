package com.example.messagingrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class MessagingRabbitmqApplicationTests {

    @MockBean
    private Runner runner;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Receiver receiver;

    @Test
    public void hareyTest() throws Exception {
        try {
            rabbitTemplate.
                    convertAndSend(MessagingRabbitmqApplication.queueName, "Hello from RabbitMQ!");

            //noinspection ResultOfMethodCallIgnored
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        } catch (AmqpConnectException ignored) {
            // ignore
        }
    }

}
