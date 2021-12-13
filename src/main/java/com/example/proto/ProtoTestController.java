package com.example.proto;

import com.example.protobuf.order.OrderEventProtoV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/proto")
public class ProtoTestController {

    private final ObjectMapper objectMapper;
    private final ProtobufHttpMessageConverter protobufHttpMessageConverter;

    @PostMapping(path = "/order")
    public ResponseEntity<OrderEventProtoV1.OrderMessageV1> getCourse(@RequestBody OrderEventProtoV1.OrderMessageV1 request) {
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
