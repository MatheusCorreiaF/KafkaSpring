package br.com.redfield.kafka.schemaregistry.producer;

import br.com.redfield.kafka.schemaregistry.entity.PessoaDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class PessoaProducerConfig {

    @Bean
    public KafkaTemplate<String, PessoaDTO> pessoaDTOTemplate(ProducerFactory<String, PessoaDTO> templatePessoaDTO){
        return new KafkaTemplate(templatePessoaDTO);
    }
}
