package com.spring.kafka.avro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AvroProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroProducerApplication.class, args);
	}

}
