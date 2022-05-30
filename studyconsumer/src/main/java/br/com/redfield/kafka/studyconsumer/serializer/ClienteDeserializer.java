package br.com.redfield.kafka.studyconsumer.serializer;

import br.com.redfield.kafka.studyconsumer.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ClienteDeserializer implements Deserializer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Cliente deserialize(String topic, byte[] data) {

        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), Cliente.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Cliente");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
