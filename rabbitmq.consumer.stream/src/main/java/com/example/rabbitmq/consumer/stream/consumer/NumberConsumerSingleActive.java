package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import org.apache.qpid.proton.amqp.messaging.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class NumberConsumerSingleActive {

    private static final Logger logger = LoggerFactory.getLogger(NumberConsumerSingleActive.class);

    //@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NAME, containerFactory = "singleActiveContainerFactory")
    public void singleActiveOne(Message message, MessageHandler.Context context) throws InterruptedException {
        Data data = (Data) message.getBody();
        String log = String.format("Single Active 1: %s, on offset: %s", data.getValue(), context.offset());
        logger.info(log);
        TimeUnit.MILLISECONDS.sleep(500);
    }

}
