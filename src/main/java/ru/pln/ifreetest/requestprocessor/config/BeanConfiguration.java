package ru.pln.ifreetest.requestprocessor.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.pln.ifreetest.requestprocessor.dao.IRequestService;
import ru.pln.ifreetest.requestprocessor.dao.RequestService;

@Configuration
public class BeanConfiguration {

	@Bean
	IRequestService getRequestService() {
		return new RequestService();
	}

	@Bean
	public Queue getAMQPQueue() {
		//TODO: get queue name from props
		return new Queue("ifree");
	}
}
