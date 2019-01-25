package ru.pln.ifreetest.requestprocessor.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "ifree")
public class MqProcessor {

	@RabbitHandler
	public void receive(String in) {
		//TODO write to db
		System.out.println(" [x] Received '" + in + "'");
	}
}
