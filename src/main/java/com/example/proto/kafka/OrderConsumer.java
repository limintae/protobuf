package com.example.proto.kafka;

import com.example.protobuf.order.OrderEventProtoV1;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "order-event-v1",
            groupId = "order-event-group-01",
            containerFactory="byteArrayContainerFactory")
    public void consumeCrackAnalysis(byte[] message, ConsumerRecord<String, String> record) throws InvalidProtocolBufferException {
        log.info("success subscribe trial crack analysis result");
        log.info(String.valueOf(message.length));
        OrderEventProtoV1.OrderMessageV1 orderMessageV1 = OrderEventProtoV1.OrderMessageV1.parseFrom(message);
        log.info(orderMessageV1.toString());
    }


}
