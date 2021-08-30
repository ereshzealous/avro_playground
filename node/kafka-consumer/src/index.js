const { Kafka } = require("kafkajs");
const {
  SchemaRegistry,
  SchemaType,
} = require("@kafkajs/confluent-schema-registry");

const main = async () => {
  const schema = {
    name: "animal2",
    namespace: "test",
    type: "record",
    fields: [
      {
        name: "kind",
        type: { type: "enum", name: "animalKind", symbols: ["CAT", "DOG"] },
      },
      { name: "name", type: "string" },
    ],
  };

  const registry = new SchemaRegistry({ host: "http://localhost:8081" });

  const { id } = await registry.register({
    type: SchemaType.AVRO,
    schema: JSON.stringify(schema),
  });

  console.log("ID is --->" + id);

  const kafka = new Kafka({
    clientId: "my-app",
    brokers: ["localhost:29092"],
  });

  const producer = kafka.producer();

  await producer.connect();

  const payload = { kind: "DOG", name: "Albert" };
  const encodedValue = await registry.encode(id, payload);
  await producer.send({
    topic: "test-topic",
    messages: [{ value: encodedValue }],
  });

  await producer.disconnect();

  const consumer = kafka.consumer({ groupId: "test-group-2" });

  await consumer.connect();
  await consumer.subscribe({ topic: "test-topic", fromBeginning: true });

  await consumer.run({
    eachMessage: async ({ message }) => {
      const decodedValue = await registry.decode(message.value);
      console.log(decodedValue);
    },
  });
};

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
