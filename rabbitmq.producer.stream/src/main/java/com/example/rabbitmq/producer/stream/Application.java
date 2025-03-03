package com.example.rabbitmq.producer.stream;

import com.example.rabbitmq.producer.stream.producer.StreamNumberProducer;
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

	@Override
	public void run(String... args) {
		streamNumberProducer.sendNumber(10, 14);
	}

}
