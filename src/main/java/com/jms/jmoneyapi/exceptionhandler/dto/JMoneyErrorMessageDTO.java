package com.jms.jmoneyapi.exceptionhandler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JMoneyErrorMessageDTO {
	private String errorMessage;
	private int statusCode;
}
