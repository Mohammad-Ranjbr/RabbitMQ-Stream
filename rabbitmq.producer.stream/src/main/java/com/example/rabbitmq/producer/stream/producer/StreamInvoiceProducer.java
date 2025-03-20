package com.example.rabbitmq.producer.stream.producer;

import com.example.rabbitmq.producer.stream.entity.Invoice;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;

@Service
public class StreamInvoiceProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitStreamTemplate rabbitStreamTemplate;

    @Autowired
    public StreamInvoiceProducer(RabbitTemplate rabbitTemplate, @Qualifier("streamInvoiceTemplate") RabbitStreamTemplate rabbitStreamTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitStreamTemplate = rabbitStreamTemplate;
    }

    public void sendInvoiceUsingRabbitTemplate(Invoice invoice){
        rabbitTemplate.convertAndSend("x.invoice", "", invoice);
    }

    public void sendInvoiceUsingRabbitStreamTemplate(Invoice invoice){
        rabbitStreamTemplate.convertAndSend(invoice);
    }

}
