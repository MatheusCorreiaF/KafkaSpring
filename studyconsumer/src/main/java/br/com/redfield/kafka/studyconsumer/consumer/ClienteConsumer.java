package br.com.redfield.kafka.studyconsumer.consumer;


import br.com.redfield.kafka.studyconsumer.model.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ClienteConsumer {

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id_cliente_consumer")
    public void consume(@Payload Cliente cliente){

        System.out.println("Cliente recebido: " + cliente.toString());

    }
}
