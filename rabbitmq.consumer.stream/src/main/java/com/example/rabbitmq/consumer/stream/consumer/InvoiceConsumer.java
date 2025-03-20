package com.example.rabbitmq.consumer.stream.consumer;

import com.example.rabbitmq.consumer.stream.config.RabbitmqStreamJsonConfig;
import com.example.rabbitmq.consumer.stream.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import lombok.RequiredArgsConstructor;
import org.apache.qpid.proton.amqp.messaging.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class InvoiceConsumer {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(InvoiceConsumer.class);

    @RabbitListener(queues = RabbitmqStreamJsonConfig.STREAM_INVOICE)
    public void listenDefault(Invoice message){
        logger.info("Listen Default : " + message);
    }

    @RabbitListener(queues =  RabbitmqStreamJsonConfig.STREAM_INVOICE, containerFactory = "invoiceContainerFactoryOne")
    public void listenWithContainerFactoryOne(String message) throws JsonProcessingException {
        logger.info("listenWithContainerFactoryOne receive JSON string : {}", message);
        Invoice invoice = objectMapper.readValue(message, Invoice.class);
        logger.info("listenWithContainerFactoryOne receive Invoice  object : {}", invoice);
    }

    @RabbitListener(queues =  RabbitmqStreamJsonConfig.STREAM_INVOICE, containerFactory = "invoiceContainerFactoryTwo")
    public void invoiceContainerFactoryTwo(Message message) throws IOException {
        Data data = (Data) message.getBody();
        Invoice invoice = objectMapper.readValue(data.getValue().getArray(), Invoice.class);
        logger.info("listenWithContainerFactoryTwo receive Invoice  object : {}", invoice);
    }

}
