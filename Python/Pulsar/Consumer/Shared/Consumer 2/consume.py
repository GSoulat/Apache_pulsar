import pulsar
from pulsar import ConsumerType
import emoji

client = pulsar.Client('pulsar://localhost:6650')
consumer = client.subscribe('my-topic', subscription_name='my-sub',
                            consumer_type=ConsumerType.Shared)

while True:
    try:
        msg = consumer.receive()
        decoded_message = msg.data().decode("utf-8")
        print(f"Received message: {decoded_message}")
        consumer.acknowledge(msg)
    except pulsar.Interrupted or Exception as e:
        print("Stop receiving messages")
        print(f"Failed to process message: {e}")
        # Signal that the message was not processed successfully
        consumer.negative_acknowledge(msg)
        break

client.close()
