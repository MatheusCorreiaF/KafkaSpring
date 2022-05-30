package br.com.redfield.kafka.studyproducer.producer;

import br.com.redfield.kafka.studyproducer.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClienteProducer {

    @Autowired
    private KafkaTemplate<String, Cliente> kafkaTemplate;

    @Value("${topic.name.producer}")
    private String topicName;

    public void sendKafka(String messageId, Cliente cliente) {
        Message<Cliente> message = MessageBuilder.withPayload(cliente)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader(KafkaHeaders.MESSAGE_KEY, messageId)
                .build();

        kafkaTemplate.send(message);
    }
}
