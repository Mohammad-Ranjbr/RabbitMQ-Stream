package com.example.rabbitmq.producer.stream.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rabbitmq.stream.Environment;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

@Configuration
public class RabbitmqStreamJsonConfig {

    public static final String STREAM_INVOICE_NAME = "s.invoice";

    //@Bean
    public Declarables rabbitmqScheme(){
        FanoutExchange exchange = ExchangeBuilder.fanoutExchange("x.invoice").durable(true).build();
        Queue queue = QueueBuilder.durable(STREAM_INVOICE_NAME).stream().build();
        Binding binding = BindingBuilder.bind(queue).to(exchange);
        return new Declarables(exchange, queue, binding);
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitStreamTemplate streamInvoiceTemplate(Environment environment, Jackson2JsonMessageConverter jackson2JsonMessageConverter){
        RabbitStreamTemplate rabbitStreamTemplate = new RabbitStreamTemplate(environment, STREAM_INVOICE_NAME);
        rabbitStreamTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitStreamTemplate;
    }

}
