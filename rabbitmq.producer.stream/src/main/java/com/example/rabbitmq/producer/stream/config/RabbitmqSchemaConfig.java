package com.example.rabbitmq.producer.stream.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqSchemaConfig {

    @Bean
    public Declarables rabbitmqSchemaDeclarables() {
        FanoutExchange fanoutExchange = ExchangeBuilder.fanoutExchange("x.number").durable(true).build();
        Queue stream = QueueBuilder.durable("s.number").stream().build();
        Binding binding = BindingBuilder.bind(stream).to(fanoutExchange);
        return new Declarables(fanoutExchange, stream, binding);
    }

}
