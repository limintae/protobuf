package com.example.proto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class ProtobufConfig {
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean(name = "protobufRestTemplate")
    RestTemplate protobufRestTemplate(ProtobufHttpMessageConverter protobufHttpMessageConverter) {
        return new RestTemplate(List.of(protobufHttpMessageConverter));
    }
}
