package com.example.pulsarproducer;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class PulsarConfig {

    @Value("${pulsar.service-url}")
    private String serviceUrl;

    @Bean
    public PulsarClient pulsarClient() throws Exception {
        return PulsarClient.builder()
                .serviceUrl(serviceUrl)
                .build();
    }

    @Bean
    public Producer<byte[]> producer(PulsarClient pulsarClient) throws Exception {
        return pulsarClient.newProducer()
                .topic("my-topic")
                .sendTimeout(10, TimeUnit.SECONDS)
                .create();
    }
}
