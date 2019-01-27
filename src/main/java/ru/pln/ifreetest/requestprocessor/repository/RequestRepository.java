package ru.pln.ifreetest.requestprocessor.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pln.ifreetest.requestprocessor.model.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
