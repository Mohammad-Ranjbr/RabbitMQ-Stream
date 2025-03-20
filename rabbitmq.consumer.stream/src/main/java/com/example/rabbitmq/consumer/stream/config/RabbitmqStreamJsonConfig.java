package com.example.rabbitmq.consumer.stream.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

@Configuration
public class RabbitmqStreamJsonConfig {

    public static final String STREAM_INVOICE = "s.invoice";

    @Bean
    public ObjectMapper getObjectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean(name = "invoiceContainerFactoryOne")
    public RabbitListenerContainerFactory<StreamListenerContainer> invoiceContainerFactoryOne(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        // Uses Non-Native Listener (messages are received as JSON strings).
        factory.setNativeListener(false);
        factory.setConsumerCustomizer((id, builder) ->
            builder.name("invoice-consumer-one").offset(OffsetSpecification.first()).singleActiveConsumer().autoTrackingStrategy()
        );
        return factory;
    }

    @Bean(name = "invoiceContainerFactoryTwo")
    public RabbitListenerContainerFactory<StreamListenerContainer> invoiceContainerFactoryTwo(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        // This method receives messages as Message Object and to extract JSON string we need to convert the data to Data Class.
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) ->
            builder.name("invoice-consumer-two").offset(OffsetSpecification.first()).singleActiveConsumer().autoTrackingStrategy()
        );
        return factory;
    }

}
