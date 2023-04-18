import pulsar
import emoji

def main():
    client = pulsar.Client('pulsar://localhost:6650')
    producer = client.create_producer('my-topic')

    for i in range(10000):
        producer.send((f'hello-pulsar in PYTHON {i} - "🐼🐼🐼🐼🐼🐼"').encode('utf-8'))

    print("finish !!!!!!!!!!!!!")
    client.close()

if __name__ == '__main__':
    main()
