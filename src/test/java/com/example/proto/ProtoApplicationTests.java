package com.example.proto;

import com.example.protobuf.address.AddressBookProtos;
import com.example.protobuf.baeldung.BaeldungTraining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@AutoConfigureMockMvc
class ProtoApplicationTests {

    @Resource(name = "protobufRestTemplate")
    RestTemplate restTemplate;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void protoTest() {
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder()
                .setId(1)
                .setName("a")
                .setEmail("a@a.com")
                .build();

        Assertions.assertEquals(person.getEmail(), "a@a.com");
    }

    @Test
    public void whenUsingRestTemplate_thenSucceed() throws Exception {
        // ResponseEntity<BaeldungTraining.Course> course = restTemplate.getForEntity("http://localhost:8080/proto/course", BaeldungTraining.Course.class);
        MvcResult mvcResult = mockMvc.perform(post("/proto/course")).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void whenUsingRestTemplate() throws Exception {
//        ProtobufHttpMessageConverter protoConverter = new ProtobufHttpMessageConverter();
//        List<HttpMessageConverter<?>> converters=new ArrayList<HttpMessageConverter<?>>();
//        converters.add(protoConverter);
//
//        RestTemplate protoRestTemplate = new RestTemplate(converters);

        BaeldungTraining.Course course = BaeldungTraining.Course.newBuilder()
                .setId(2)
                .setCourseName("REST with spring")
                .build();

        ResponseEntity<BaeldungTraining.Course> response = restTemplate.postForEntity(
                "http://localhost:8080/proto/course",
                course,
                BaeldungTraining.Course.class
        );
        Assertions.assertEquals(1, 1);
    }

}
