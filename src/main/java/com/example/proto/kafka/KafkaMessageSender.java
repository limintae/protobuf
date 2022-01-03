package com.example.proto.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service("KafkaMessageSender")
public class KafkaMessageSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTemplate<String, byte[]> protobufKafkaTemplate;

    KafkaMessageSender(
            @Qualifier("defaultKafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate,
            @Qualifier("protobufKafkaTemplate") KafkaTemplate<String, byte[]> protobufKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.protobufKafkaTemplate = protobufKafkaTemplate;
    }

    public void send(Object payload, String topicName) {
        Message<Object> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        ListenableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info(
                        "Sent message=[" + stringObjectSendResult.getProducerRecord().value().toString() +
                                "] with offset=[" + stringObjectSendResult.getRecordMetadata().offset() + "]"
                );
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[] due to : " + ex.getMessage());
            }

        });
    }

    public void sendProtoMessageByte(byte[] payload, String topicName) throws ExecutionException, InterruptedException {
        Message<?> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        ListenableFuture<SendResult<String, byte[]>> future = protobufKafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, byte[]> sendResult) {
                byte[] byteMessage = sendResult.getProducerRecord().value();
                log.info(
                        "Sent byte message length=[" + byteMessage.length +
                                "] with offset=[" + sendResult.getRecordMetadata().offset() + "]"
                );
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[] due to : " + ex.getMessage());
            }

        });
    }

}
