package ru.pln.ifreetest.requestprocessor.dao;

import ru.pln.ifreetest.requestprocessor.model.Request;

public class RequestService implements IRequestService {
	@Override
	public Request process(Request request) throws Exception {
		Thread.sleep(1000);
		return request;
	}

	@Override
	public Request findById(Long id) {
		//TODO database
		return new Request(id, "test");
	}
}