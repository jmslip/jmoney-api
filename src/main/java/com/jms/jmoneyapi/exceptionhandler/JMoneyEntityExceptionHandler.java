package com.jms.jmoneyapi.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class JMoneyEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ex.getRootCause();

		if (rootCause instanceof UnrecognizedPropertyException) {
			MensagemErro exceptionMessage = montaMensagemExcecao(rootCause, status.value());

			return handleExceptionInternal(ex, exceptionMessage, headers, status, request);
		}

		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	private MensagemErro montaMensagemExcecao(Throwable rootCause, int statusCode) {
		StringBuilder exceptionMessage = new StringBuilder();

		exceptionMessage
		.append(messageSource.getMessage("atributo.invalido", null, LocaleContextHolder.getLocale()));
		exceptionMessage.append(": ");
		exceptionMessage.append(((UnrecognizedPropertyException) rootCause).getPropertyName());

		return new MensagemErro(exceptionMessage.toString(), statusCode);
	}

	@Getter
	@Setter
	@AllArgsConstructor
	private class MensagemErro {

		private String errorMessage;
		private int statusCode;
	}

}
