### Node consumer for Java Avro Producer

This node applocation has a consumer.js file which will listen to topic that has messages published by Java Avro producer backed by confluent schema registry.

### Code In Action

The docker compose file in spring boot project is sufficient. No need to start again.
Just do

```shell
yarn install
cd src
node consumer.js
```

The above consumer will generate error while reading message from topic `magic byte error`
