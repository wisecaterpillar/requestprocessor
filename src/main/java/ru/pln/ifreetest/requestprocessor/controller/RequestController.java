package ru.pln.ifreetest.requestprocessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pln.ifreetest.requestprocessor.model.Reply;
import ru.pln.ifreetest.requestprocessor.model.Request;
import ru.pln.ifreetest.requestprocessor.model.RequestStatus;
import ru.pln.ifreetest.requestprocessor.repository.RequestRepository;
import ru.pln.ifreetest.requestprocessor.service.RequestSender;

import java.util.Optional;

@RestController
public class RequestController {

    private final RequestSender requestSender;
    private final RequestRepository requestRepository;

    @Autowired
    public RequestController(RequestSender requestSender, RequestRepository requestRepository) {
        this.requestSender = requestSender;
        this.requestRepository = requestRepository;
    }

    @RequestMapping("/accept")
    public ResponseEntity accept(Request request) {
        if (request.getId() == null || requestRepository.existsById(request.getId()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);//400
        requestSender.sendRequest(request);
        return new ResponseEntity(HttpStatus.OK); //200
    }

    @RequestMapping("/get/{id}")
    public Reply accept(@PathVariable Long id) {
        Optional<Request> optRequest = requestRepository.findById(id);
        return optRequest.map(request -> new Reply(request, RequestStatus.ACCEPTED)).orElseGet(() -> new Reply(null, RequestStatus.NOT_ACCEPTED));
    }
}
