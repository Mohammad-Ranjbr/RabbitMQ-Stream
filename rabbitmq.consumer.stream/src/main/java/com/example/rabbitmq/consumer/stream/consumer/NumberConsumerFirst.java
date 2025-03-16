package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class NumberConsumerFirst {

    private static final Logger logger = LoggerFactory.getLogger(NumberConsumerFirst.class);

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "firstContainerFactoryOne")
    public void absoluteOne(Message message, MessageHandler.Context context) {
        logger.info("First One: {}, on offset {}", message, context.offset());
    }

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "firstContainerFactoryTwo")
    public void absoluteTwo(Message message, MessageHandler.Context context) {
        logger.info("First 2: {}, on offset {}", message, context.offset());
    }

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "firstContainerFactoryThree")
    public void absoluteThree(Message message, MessageHandler.Context context) {
        logger.info("First 3: {}, on offset {}", message, context.offset());
        context.storeOffset();
    }

}
