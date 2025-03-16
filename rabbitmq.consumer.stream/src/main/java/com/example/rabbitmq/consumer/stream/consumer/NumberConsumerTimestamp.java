package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NumberConsumerTimestamp {

    private static final Logger logger = LoggerFactory.getLogger(NumberConsumerTimestamp.class);

//    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "timestampContainerFactoryOne")
//    public void absoluteOne(Message message, MessageHandler.Context context) {
//        logger.info("Timestamp One: {}, on offset {}", message, context.offset());
//    }

    //@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "timestampContainerFactoryTwo")
    public void absoluteTwo(Message message, MessageHandler.Context context) {
        logger.info("Timestamp 2: {}, on offset {}", message, context.offset());
    }

}
