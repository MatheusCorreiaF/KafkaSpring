package br.com.redfield.kafka.studyproducer.producer;

import br.com.redfield.kafka.studyproducer.model.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ClienteProducerConfig {

    @Bean
    public KafkaTemplate<String, Cliente> clienteTemplate(ProducerFactory<String, Cliente> templateCliente){
        return new KafkaTemplate(templateCliente);
    }
}
