package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class NumberConsumerAbsolute {

    private static final Logger logger = LoggerFactory.getLogger(NumberConsumerAbsolute.class);

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "absoluteContainerFactoryOne")
    public void absoluteOne(Message message, MessageHandler.Context context) {
        logger.info("Absolute One: {}, on offset {}", message, context.offset());
    }

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "absoluteContainerFactoryTwo")
    public void absoluteTwo(Message message, MessageHandler.Context context) {
        logger.info("Absolute 2: {}, on offset {}", message, context.offset());
    }

}
