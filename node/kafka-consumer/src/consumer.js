const { Kafka } = require("kafkajs");
const {
  SchemaRegistry,
  SchemaType,
} = require("@kafkajs/confluent-schema-registry");

/* let {
  MAGIC_BYTE,
} = require("@kafkajs/confluent-schema-registry/dist/wireEncoder");
MAGIC_BYTE = Buffer.alloc(72, 0, "hex");
 */
const kafka = new Kafka({ clientId: "my-app", brokers: ["localhost:29092"] });
const registry = new SchemaRegistry({ host: "http://localhost:8081/" });
const consumer = kafka.consumer({ groupId: "test-group" });
const run = async () => {
  await consumer.connect();
  await consumer.subscribe({
    topic: "com.spring.kafka.avro.newuser",
    fromBeginning: true,
  });

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      // const decodedKey = await registry.decode(message.key);
      const decodedValue = await registry.decode(message.value);
      console.log({ decodedKey, decodedValue });
    },
  });
};

run().catch(console.error);
