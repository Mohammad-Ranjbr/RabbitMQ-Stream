package com.example.rabbitmq.producer.stream.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamNumberProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public StreamNumberProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNumber(int start, int end) {
        for (int i = start; i <= end; i++) {
            rabbitTemplate.convertAndSend("x.number", "", "Number " + i);
        }
    }

}
