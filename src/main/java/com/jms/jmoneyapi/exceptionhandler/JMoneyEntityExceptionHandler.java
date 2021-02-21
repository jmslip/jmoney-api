package com.jms.jmoneyapi.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.jms.jmoneyapi.exceptionhandler.dto.JMoneyErrorMessageDTO;

@ControllerAdvice
public class JMoneyEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(JMoneyEntityExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ex.getRootCause();

		if (rootCause instanceof UnrecognizedPropertyException) {
			JMoneyErrorMessageDTO exceptionMessage = montaMensagemExcecao(rootCause, status.value());

			LOG.error(ex.getMessage(), ex);
			return handleExceptionInternal(ex, exceptionMessage, headers, status, request);
		}

		LOG.error(ex.getMessage(), ex);
		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	private JMoneyErrorMessageDTO montaMensagemExcecao(Throwable rootCause, int statusCode) {
		StringBuilder exceptionMessage = new StringBuilder();

		exceptionMessage
		.append(messageSource.getMessage("atributo.invalido", null, LocaleContextHolder.getLocale()));
		exceptionMessage.append(": ");
		exceptionMessage.append(((UnrecognizedPropertyException) rootCause).getPropertyName());

		return new JMoneyErrorMessageDTO(exceptionMessage.toString(), statusCode);
	}

}
