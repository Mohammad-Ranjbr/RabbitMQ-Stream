package com.example.rabbitmq.producer.stream;

import com.example.rabbitmq.producer.stream.producer.StreamNumberProducer;
import com.example.rabbitmq.producer.stream.producer.SuperStreamNumberProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final StreamNumberProducer streamNumberProducer;
	private final SuperStreamNumberProducer superStreamNumberProducer;

	@Override
	public void run(String... args) {
		//streamNumberProducer.sendNumber(0, 10_000);
		superStreamNumberProducer.sendNumberUsingRabbitTemplate(0, 10);
		superStreamNumberProducer.sndNumberUsingRabbitStreamTemplate(10, 20);
	}

}
