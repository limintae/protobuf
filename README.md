# 프로토콜버퍼로 java class 생성
- .\protoc --proto_path=. --java_out=. .\baeldung.proto
- .\protoc --proto_path=. --java_out=.\src\main\java .\src\proto\addressbook.proto
- .\protoc --proto_path=. --java_out=.\src\main\java .\src\proto\address-service\*.proto
- .\protoc --proto_path=. --java_out=.\src\main\java .\src\proto\baeldung-service\*.proto