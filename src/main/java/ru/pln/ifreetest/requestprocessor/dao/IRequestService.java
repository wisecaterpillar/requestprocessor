package ru.pln.ifreetest.requestprocessor.dao;

import ru.pln.ifreetest.requestprocessor.model.Request;

public interface IRequestService {
	Request process(Request request) throws Exception;

	Request findById(Long id);
}
