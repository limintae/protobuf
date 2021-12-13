# Overview
1. docker-compose 를 활용한 Kafka 설치 및 토픽 생성
2. Kafka protobuf 이벤트 발행/구독 테스트

# .proto to java class
```
# windows
$ .\protoc --proto_path=. --java_out=.\src\main\java .\src\proto\order\*.proto
```