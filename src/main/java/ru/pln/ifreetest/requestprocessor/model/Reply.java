package ru.pln.ifreetest.requestprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reply {
	Request request;
	RequestStatus status;
}