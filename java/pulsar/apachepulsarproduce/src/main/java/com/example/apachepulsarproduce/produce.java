package com.example.pulsarproducer;

import org.apache.pulsar.client.api.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PulsarProducerController {

    @Autowired
    private Producer<byte[]> producer;

    @GetMapping("/produce")
    public String produceMessage(@RequestParam("message") String message) throws Exception {
        producer.send(message.getBytes());
        return "Message sent: " + message;
    }
}
