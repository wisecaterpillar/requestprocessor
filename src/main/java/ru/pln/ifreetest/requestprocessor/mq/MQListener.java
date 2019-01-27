package ru.pln.ifreetest.requestprocessor.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pln.ifreetest.requestprocessor.model.Request;
import ru.pln.ifreetest.requestprocessor.repository.RequestRepository;

@Component
@RabbitListener(queues = "${rabbitmq.queue}")
public class MQListener {

    private final RequestRepository requestRepository;

    @Autowired
    public MQListener(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @RabbitHandler
    public void receive(Request request) {
        try {
            requestRepository.save(process(request));
            System.out.println(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Request process(Request request) throws Exception {
        Thread.sleep(1000);
        return request;
    }
}

