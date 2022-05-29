package br.com.redfield.kafka.schemaregistry.producer;

import br.com.redfield.kafka.schemaregistry.entity.Pessoa;
import br.com.redfield.kafka.schemaregistry.entity.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;

@Component
public class PessoaProducerImpl {

    @Autowired
    KafkaTemplate<String, PessoaDTO> pessoaTemplate;

    private static final String TOPIC = "Pessoa";

    public void persist(String messageId, Pessoa pessoa) {

        PessoaDTO pessoaDTO = convertPessoaToPessoaDTO(pessoa);

        sendMessageToTopic(messageId, pessoaDTO);

    }

    private void sendMessageToTopic(String messageId, PessoaDTO pessoaDTO) {

        Message<PessoaDTO> message = creatingMessageWithHeader(messageId, pessoaDTO, TOPIC);

        ListenableFuture<SendResult<String, PessoaDTO>> future = pessoaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, PessoaDTO>>() {

            @Override
            public void onSuccess(SendResult<String, PessoaDTO> result) {
                System.out.println("Pessoa enviada. MessageId " + result.getProducerRecord().headers().headers(KafkaHeaders.MESSAGE_KEY));

            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Erro no envio. MessageId $messageId");

            }

        });
    }

    private Message<PessoaDTO> creatingMessageWithHeader(String messageId, PessoaDTO pessoaDTO, String topic) {

        return MessageBuilder.withPayload(pessoaDTO)
                .setHeader("hash", pessoaDTO.hashCode())
                .setHeader("version", "1.0.0")
                .setHeader("endOfLife", LocalDate.now().plusDays(1L))
                .setHeader("type", "fct")
                .setHeader("cid", messageId)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, messageId)
                .build();
    }

    private PessoaDTO convertPessoaToPessoaDTO(Pessoa pessoa) {
        //newBuilder é proveniente do Avro
        return PessoaDTO.newBuilder()
                .setName(pessoa.getName())
                .setLastName(pessoa.getLastName())
                .build();
    }
}
