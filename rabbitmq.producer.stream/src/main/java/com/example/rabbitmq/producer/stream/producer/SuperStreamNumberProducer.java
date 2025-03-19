package com.example.rabbitmq.producer.stream.producer;

import com.example.rabbitmq.producer.stream.config.RabbitmqSuperStreamConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;

@Service
public class SuperStreamNumberProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitStreamTemplate rabbitStreamTemplate;

    @Autowired
    public SuperStreamNumberProducer(RabbitTemplate rabbitTemplate, @Qualifier("superStreamNumberTemplate") RabbitStreamTemplate rabbitStreamTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitStreamTemplate = rabbitStreamTemplate;
    }

    public void sendNumberUsingRabbitTemplate(int start, int end){
        for(int i=start; i<end; i++){
            String message = "Number " + i;
            // This method ensures that messages are distributed evenly across partitions.
            String routingKey = Integer.toString(i % RabbitmqSuperStreamConfig.SUPER_STREAM_NUMBER_PARTITIONS);
            rabbitTemplate.convertAndSend(RabbitmqSuperStreamConfig.SUPER_STREAM_NUMBER_NAME, routingKey, message);
        }
        System.out.printf("Sent super stream %d to %d", start, (end-1));
    }

    public void sndNumberUsingRabbitStreamTemplate(int start, int end){
        for(int i=start; i<end; i++){
            String str = "Number " + i;
            // RabbitMQ automatically hashes the messageId and decides which partition the message should be placed in based on that.
            // Messages with the same messageId are always placed in the same partition and their order is preserved.
            // You can not specify the messageId and in this case RabbitMQ will automatically generate a random ID for the message.
            var message = rabbitStreamTemplate.messageBuilder().addData(str.getBytes()).properties().messageId(i).messageBuilder().build();
            rabbitStreamTemplate.send(message);
        }
        System.out.printf("Sent super stream %d to%d", start, (end-1));
    }

}
