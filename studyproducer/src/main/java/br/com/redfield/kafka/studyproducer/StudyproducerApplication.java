package br.com.redfield.kafka.studyproducer;

import br.com.redfield.kafka.studyproducer.model.Cliente;
import br.com.redfield.kafka.studyproducer.model.Endereco;
import br.com.redfield.kafka.studyproducer.producer.ClienteProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class StudyproducerApplication implements ApplicationRunner {

	@Autowired
	private ClienteProducer clienteProducer;

	public static void main(String[] args) {
		SpringApplication.run(StudyproducerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Cliente cliente = new Cliente("Cliente1", "64 34613461", new Endereco("Rua01", "Bairro01", "Goiania"));
		clienteProducer.sendKafka("MessageTeste", cliente);
	}
}
