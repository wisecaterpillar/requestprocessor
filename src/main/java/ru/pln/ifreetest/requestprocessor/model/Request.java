package ru.pln.ifreetest.requestprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
	Long id;
	String message;
}
