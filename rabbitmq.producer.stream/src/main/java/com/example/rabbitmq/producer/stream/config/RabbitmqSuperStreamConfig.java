package com.example.rabbitmq.producer.stream.config;

import com.rabbitmq.stream.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.SuperStream;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

@Configuration
public class RabbitmqSuperStreamConfig {

    public static final String SUPER_STREAM_NUMBER_NAME = "s.super.number";
    public static final int SUPER_STREAM_NUMBER_PARTITIONS = 3;

    @Bean
    public SuperStream superStreamNumber(){
        return new SuperStream(SUPER_STREAM_NUMBER_NAME, SUPER_STREAM_NUMBER_PARTITIONS);
    }

    // This method creates a RabbitStreamTemplate called superStreamNumberTemplate which is used to send messages to the SuperStream.
    // The setSuperStreamRouting method is responsible for specifying a method to distribute messages between partitions.
    // Here, messageId is used as the message distribution key.
    // @Qualifier is used only during dependency injection and not during bean definition.
    @Bean(name = "superStreamNumberTemplate")
    public RabbitStreamTemplate superStreamTemplate(Environment environment){
        RabbitStreamTemplate template = new RabbitStreamTemplate(environment, SUPER_STREAM_NUMBER_NAME);
        template.setSuperStreamRouting(message -> message.getProperties().getMessageIdAsString());
        return template;
    }

}
