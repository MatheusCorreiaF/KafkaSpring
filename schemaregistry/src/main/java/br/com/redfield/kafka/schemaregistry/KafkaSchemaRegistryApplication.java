package br.com.redfield.kafka.schemaregistry;

import br.com.redfield.kafka.schemaregistry.entity.Pessoa;
import br.com.redfield.kafka.schemaregistry.producer.PessoaProducerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSchemaRegistryApplication implements ApplicationRunner {

	@Autowired
	private PessoaProducerImpl pessoaProducerImpl;


	public static void main(String[] args) {
		SpringApplication.run(KafkaSchemaRegistryApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Pessoa pessoa = new Pessoa("TesteNomeConsumer", "TesteSobrenomeConsumer");
		Thread.sleep(5000);
		pessoaProducerImpl.persist("messagemTeste", pessoa);
	}
}
