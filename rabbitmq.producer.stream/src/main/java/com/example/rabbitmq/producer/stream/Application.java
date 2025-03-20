package com.example.rabbitmq.producer.stream;

import com.example.rabbitmq.producer.stream.entity.Invoice;
import com.example.rabbitmq.producer.stream.producer.StreamInvoiceProducer;
import com.example.rabbitmq.producer.stream.producer.StreamNumberProducer;
import com.example.rabbitmq.producer.stream.producer.SuperStreamInvoiceProducer;
import com.example.rabbitmq.producer.stream.producer.SuperStreamNumberProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final StreamNumberProducer streamNumberProducer;
	private final SuperStreamNumberProducer superStreamNumberProducer;
	private final StreamInvoiceProducer streamInvoiceProducer;
	private final SuperStreamInvoiceProducer superStreamInvoiceProducer;
	private final RabbitTemplate rabbitTemplate;

	@Override
	public void run(String... args) throws JsonProcessingException {
		//streamNumberProducer.sendNumber(0, 10_000);
		//superStreamNumberProducer.sendNumberUsingRabbitTemplate(0, 10);
		//superStreamNumberProducer.sndNumberUsingRabbitStreamTemplate(10, 20);

//		for(int i=0; i<10; i++){
//			Invoice invoice = new Invoice(ThreadLocalRandom.current().nextInt(100, 1000), Invoice.Status.CREATED, "Invoice " + i);
//			if(i % 2 == 0){
//				streamInvoiceProducer.sendInvoiceUsingRabbitTemplate(invoice);
//			} else {
//				streamInvoiceProducer.sendInvoiceUsingRabbitStreamTemplate(invoice);
//			}
//		}

		rabbitTemplate.convertAndSend("Test");
		for(int i=0; i<10; i++){
			var invoiceAmount = ThreadLocalRandom.current().nextInt(100, 1000);
			var invoiceCreated = new Invoice(invoiceAmount, Invoice.Status.CREATED, "INV-" + i);
			var invoiceApproved = new Invoice(invoiceAmount, Invoice.Status.APPROVED, "INV-" + i);

			superStreamInvoiceProducer.sendInvoiceUsingRabbitStreamTemplate(invoiceCreated);
			superStreamInvoiceProducer.sendInvoiceUsingRabbitStreamTemplate(invoiceApproved);
		}

		System.out.println("All invoices sent");

	}

}
