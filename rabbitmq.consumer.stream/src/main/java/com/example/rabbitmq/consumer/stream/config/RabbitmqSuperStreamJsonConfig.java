package com.example.rabbitmq.consumer.stream.config;

import com.example.rabbitmq.consumer.stream.entity.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import java.util.concurrent.TimeUnit;

@Configuration
public class RabbitmqSuperStreamJsonConfig {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqSuperStreamJsonConfig.class);

    @Autowired
    public RabbitmqSuperStreamJsonConfig(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Bean(name = "superStreamInvoiceContainer")
    public StreamListenerContainer superStreamInvoiceContainer(){
        Environment environment = Environment.builder().maxConsumersByConnection(1)
                .username("admin")
                .password("admin123")
                .host("localhost")
                .port(5552)
                .build();
        StreamListenerContainer container = new StreamListenerContainer(environment);

        container.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
        container.superStream("s.super.invoice", "my-super-stream-invoice-consumer");
        container.setupMessageListener(message -> {
            try {
                Invoice invoice = objectMapper.readValue(message.getBody(), Invoice.class);
                logger.info("Invoice is : {}", invoice);
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println();
        });
        return container;
    }

}
