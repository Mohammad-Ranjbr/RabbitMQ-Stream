package com.example.rabbitmq.consumer.stream.config;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import java.util.concurrent.TimeUnit;

@Configuration
public class RabbitmqSuperStreamConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqSuperStreamConfig.class);

    // It is responsible for managing the connection to RabbitMQ and reading messages from Superstream.
    @Bean
    public StreamListenerContainer superStreamNumberContainer(){
        Environment environment = Environment.builder().maxConsumersByConnection(1)
                .username("admin")
                .password("admin123")
                .host("localhost")
                .port(5552)
                .build();
        StreamListenerContainer container = new StreamListenerContainer(environment);

        container.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
        // It connects to Superstream with the name s.super.number. my-super-stream-number-container is the name of the consumer that should be used to process the data.
        container.superStream("s.super.number", "my-super-stream-number-container");
        container.setupMessageListener(message -> {
            logger.info(new String(message.getBody()));
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return container;
    }

}
