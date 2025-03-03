package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NumberConsumerLast {

    private static final Logger logger = LoggerFactory.getLogger(NumberConsumerLast.class);

    @RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "lastContainerFactoryOne")
    public void absoluteOne(Message message, MessageHandler.Context context) {
        logger.info("Last One: {}, on offset {}", message, context.offset());
    }

}
