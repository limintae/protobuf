package com.example.proto.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Kafka consumer configuration class
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private final List<String> kafkaServers;

    public KafkaConsumerConfig() {
        List<String> kafkaServers = new ArrayList<>();
        kafkaServers.add("127.0.0.1:9094");
        this.kafkaServers = kafkaServers;
    }

    /**
     * Kafka byteArray ConfigMap
     * @return
     */
    private Map<String, Object> kafkaByteArrayConfigMap() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, String.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return config;
    }

    /**
     * byteArrayContainerFactory
     * @return
     */
    @Bean("byteArrayContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, byte[]>> byteArrayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                kafkaByteArrayConfigMap()
        ));
        return listener;
    }

}

