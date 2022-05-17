package br.com.redfield.kafka.schemaregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSchemaRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSchemaRegistryApplication.class, args);
	}

}