## Spring Cloud Application using Kafka, Avro and Confluent Schema Registry

### Setup infra
``` shell
$ docker-compose up -d
Then check whether all processes are running or not
$ docker-compose ps
          Name                        Command                  State                                               Ports
-----------------------------------------------------------------------------------------------------------------------------------------------------------------
kafka                      /etc/confluent/docker/run        Up (healthy)   0.0.0.0:29092->29092/tcp,:::29092->29092/tcp, 0.0.0.0:9092->9092/tcp,:::9092->9092/tcp
kafka-manager              /kafka-manager/bin/cmak -D ...   Up (healthy)   0.0.0.0:9000->9000/tcp,:::9000->9000/tcp
kafka-rest-proxy           /etc/confluent/docker/run        Up (healthy)   0.0.0.0:8082->8082/tcp,:::8082->8082/tcp
kafka-schema-registry-ui   /run.sh                          Up (healthy)   0.0.0.0:8001->8000/tcp,:::8001->8000/tcp
kafka-topics-ui            /run.sh                          Up (healthy)   0.0.0.0:8085->8000/tcp,:::8085->8000/tcp
schema-registry            /etc/confluent/docker/run        Up (healthy)   0.0.0.0:8081->8081/tcp,:::8081->8081/tcp
zookeeper                  /etc/confluent/docker/run        Up (healthy)   0.0.0.0:2181->2181/tcp,:::2181->2181/tcp, 2888/tcp, 3888/tcp
```
### Start Producer
Navigate to avro-producer and run `AvroProducerApplication.java as Java Application`. The producer starts and will publish messages every 15 seconds.

### Start Consumer
Navigate to avro-consumer and run `AvroConsumerApplication.java as Java Application`. The application will consume application.
``` txt
Payload: {"eventId": "81d29d7d-61dc-432a-82fa-d01e2ddf2641", "eventType": "CREATED", "eventTimestamp": 1630318194, "id": "a9cd4ea1-eed3-44b8-b439-a1a1a44e7528", "firstName": "IpQClcXMJt", "lastName": "GYclcxzHLg", "email": "hlEvLqROxd@gmail.com", "mobileNumber": "8519213004", "city": "hxqLkKfJnM", "country": "India", "createdOn": 1630318194, "updatedOn": 1630318194}
---
2021-08-30 15:40:09.902  INFO 8780 --- [container-0-C-1] c.spring.kafka.avro.config.UsersStream   : 
---
Headers: {kafka_offset=5, scst_nativeHeadersPresent=true, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@78884d2, deliveryAttempt=1, kafka_timestampType=CREATE_TIME, id=f126d3b5-766a-5f0d-3d5f-b8725674a43f, kafka_receivedPartitionId=1, contentType=application/vnd.userevent.v1+avro, kafka_receivedTopic=com.spring.kafka.avro.newuser, kafka_receivedTimestamp=1630318209896, kafka_groupId=eventServiceGroup, timestamp=1630318209902}

Payload: {"eventId": "b6dc8b64-09ed-48cf-bf3a-9952503b4dba", "eventType": "CREATED", "eventTimestamp": 1630318209, "id": "e000eeed-cdba-4ad5-b533-1b33d33741e1", "firstName": "eIlbDVbkPy", "lastName": "xMAOxGNpCs", "email": "KlmLemJnjr@gmail.com", "mobileNumber": "5293261480", "city": "QhuVcZHiwK", "country": "India", "createdOn": 1630318209, "updatedOn": 1630318209}
---
2021-08-30 15:40:24.908  INFO 8780 --- [container-0-C-1] c.spring.kafka.avro.config.UsersStream   : 
---
Headers: {kafka_offset=3, scst_nativeHeadersPresent=true, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@78884d2, deliveryAttempt=1, kafka_timestampType=CREATE_TIME, id=f9142277-b2ec-ec64-7110-ccc4e459cbab, kafka_receivedPartitionId=2, contentType=application/vnd.userevent.v1+avro, kafka_receivedTopic=com.spring.kafka.avro.newuser, kafka_receivedTimestamp=1630318224898, kafka_groupId=eventServiceGroup, timestamp=1630318224907}

Payload: {"eventId": "bb1ebdfa-0c89-4e3b-bc09-ca75a9e97499", "eventType": "CREATED", "eventTimestamp": 1630318224, "id": "3860d793-92b7-45aa-9376-d8fae56ff400", "firstName": "STyxSntbPx", "lastName": "WZLkrEYMcF", "email": "pXuaOKybUK@gmail.com", "mobileNumber": "1099000614", "city": "OeVayksHLT", "country": "India", "createdOn": 1630318224, "updatedOn": 1630318224}
---
```