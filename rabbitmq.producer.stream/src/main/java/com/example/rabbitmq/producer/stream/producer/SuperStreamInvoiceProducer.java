package com.example.rabbitmq.producer.stream.producer;

import com.example.rabbitmq.producer.stream.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;

@Service
public class SuperStreamInvoiceProducer {

    private final ObjectMapper objectMapper;
    private final RabbitStreamTemplate rabbitStreamTemplate;

    @Autowired
    public SuperStreamInvoiceProducer(@Qualifier("superStreamInvoiceTemplate") RabbitStreamTemplate rabbitStreamTemplate,
                                      ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.rabbitStreamTemplate = rabbitStreamTemplate;
    }

    public void sendInvoiceUsingRabbitStreamTemplate(Invoice invoice) throws JsonProcessingException {
        byte[] jsonBytes = objectMapper.writeValueAsBytes(invoice);
        Message message = rabbitStreamTemplate.messageBuilder().addData(jsonBytes).properties()
                .messageId(invoice.getInvoiceNumber())
                .messageBuilder().build();
        rabbitStreamTemplate.send(message);
    }

}
