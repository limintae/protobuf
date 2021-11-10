package com.example.proto;

import com.example.protobuf.address.AddressBookProtos;
import com.example.protobuf.baeldung.BaeldungTraining;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/person")
    public ResponseEntity<String> test(@RequestBody String person) {
        AddressBookProtos.Person person1 = objectMapper.convertValue(person, AddressBookProtos.Person.class);
        return new ResponseEntity<>(person1.getName(), HttpStatus.OK);
    }

    @PostMapping(path = "/course")
    public ResponseEntity<BaeldungTraining.Course> getCourse(@RequestBody BaeldungTraining.Course request) {
//        BaeldungTraining.Course course = BaeldungTraining.Course.newBuilder()
//                .setId(1)
//                .setCourseName("REST with spring")
//                .build();

        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
