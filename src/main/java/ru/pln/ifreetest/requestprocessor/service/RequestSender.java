package ru.pln.ifreetest.requestprocessor.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pln.ifreetest.requestprocessor.model.Request;

@Service
public class RequestSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Autowired
    public RequestSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRequest(Request request) {
        this.rabbitTemplate.convertAndSend(queueName, request);
    }
}