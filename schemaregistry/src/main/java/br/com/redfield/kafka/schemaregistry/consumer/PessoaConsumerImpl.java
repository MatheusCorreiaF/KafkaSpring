package br.com.redfield.kafka.schemaregistry.consumer;

import br.com.redfield.kafka.schemaregistry.entity.Pessoa;
import br.com.redfield.kafka.schemaregistry.entity.PessoaDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PessoaConsumerImpl {

    //@KafkaListener(topics = {"Pessoa"}, groupId = "pessoa-consumer") //Lê a partir do offset
    //Lê tudo
    @KafkaListener(id = "pessoa-consumer",
            topicPartitions = {
                    @TopicPartition(
                            topic = "Pessoa",
                            partitions ={"0"},
                            partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0")
                    )
            }
                )
    public void consumerPessoa(@Payload PessoaDTO pessoaDTO) {

        Pessoa pessoa = new Pessoa(pessoaDTO.getName().toString(), pessoaDTO.getLastName().toString());

        System.out.println("Pessoa recebida: " + pessoa.toString());
    }
}
