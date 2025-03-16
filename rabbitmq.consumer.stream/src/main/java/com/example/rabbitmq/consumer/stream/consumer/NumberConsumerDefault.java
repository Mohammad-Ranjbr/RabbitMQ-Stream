package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class NumberConsumerDefault {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME)
    public void listenNumberOne(String message){
        logger.info("NumberConsumerDefault listenNumberOne: {}", message);
    }

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME)
    public void listenNumberTwo(Message message){
        logger.info("NumberConsumerDefault listenNumberTwo: {}", message);
    }

}
