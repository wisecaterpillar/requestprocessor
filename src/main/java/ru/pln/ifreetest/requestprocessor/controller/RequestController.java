package ru.pln.ifreetest.requestprocessor.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pln.ifreetest.requestprocessor.dao.IRequestService;
import ru.pln.ifreetest.requestprocessor.model.Reply;
import ru.pln.ifreetest.requestprocessor.model.Request;
import ru.pln.ifreetest.requestprocessor.model.RequestStatus;

@RestController
public class RequestController {

	@Autowired
	RabbitTemplate template;

	@Autowired
	Queue queue;

	@Autowired
	IRequestService requestService;

	@RequestMapping("/accept")
	public ResponseEntity accept(Request request) {
		try {
			requestService.process(request);
		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); //400
		}
		template.convertAndSend(queue.getName(), request);
		return new ResponseEntity(HttpStatus.OK); //200
	}

	@RequestMapping("/get/{id}")
	public Reply accept(@PathVariable Long id) {
		//TODO get from database
		Request request = requestService.findById(id);
		return new Reply(request, request.equals(null) ? RequestStatus.NOT_ACCEPTED : RequestStatus.ACCEPTED);
	}
}
