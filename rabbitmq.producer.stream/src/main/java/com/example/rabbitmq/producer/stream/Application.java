package com.example.rabbitmq.producer.stream;

import com.example.rabbitmq.producer.stream.entity.Invoice;
import com.example.rabbitmq.producer.stream.producer.StreamInvoiceProducer;
import com.example.rabbitmq.producer.stream.producer.StreamNumberProducer;
import com.example.rabbitmq.producer.stream.producer.SuperStreamNumberProducer;
import lombok.RequiredArgsConstructor;
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

	@Override
	public void run(String... args) {
		//streamNumberProducer.sendNumber(0, 10_000);
		//superStreamNumberProducer.sendNumberUsingRabbitTemplate(0, 10);
		//superStreamNumberProducer.sndNumberUsingRabbitStreamTemplate(10, 20);

		for(int i=0; i<10; i++){
			Invoice invoice = new Invoice(ThreadLocalRandom.current().nextInt(100, 1000), Invoice.Status.CREATED, "Invoice " + i);
			if(i % 2 == 0){
				streamInvoiceProducer.sendInvoiceUsingRabbitTemplate(invoice);
			} else {
				streamInvoiceProducer.sendInvoiceUsingRabbitStreamTemplate(invoice);
			}
		}
	}

}
