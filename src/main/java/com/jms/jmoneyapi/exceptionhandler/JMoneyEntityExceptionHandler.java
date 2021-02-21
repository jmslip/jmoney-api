package com.jms.jmoneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
			List<JMoneyErrorMessageDTO> exceptionMessage = getErrorMessageList(rootCause, null, status.value(),
					"atributo.invalido");

			return logErrorAndHandleExceptionInternal(ex, headers, status, request, exceptionMessage);
		}

		LOG.error(ex.getMessage(), ex);
		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	private ResponseEntity<Object> logErrorAndHandleExceptionInternal(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request, List<JMoneyErrorMessageDTO> exceptionMessage) {
		LOG.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, exceptionMessage, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<JMoneyErrorMessageDTO> errorList = getErrorMessageList(ex.getCause(), ex.getBindingResult(),
				status.value(), null);
		return handleExceptionInternal(ex, errorList, headers, status, request);
	}

	private List<JMoneyErrorMessageDTO> getErrorMessageList(Throwable rootCause, BindingResult bindindResult,
			int statusCode, String code) {
		List<JMoneyErrorMessageDTO> erros = new ArrayList<>();

		if (Objects.nonNull(bindindResult)) {
			for (FieldError fieldError : bindindResult.getFieldErrors()) {
				String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				erros.add(new JMoneyErrorMessageDTO(mensagem, statusCode));
			}
		} else {
			erros.add(getErrorMessage(rootCause, statusCode, code));
		}

		return erros;
	}

	private JMoneyErrorMessageDTO getErrorMessage(Throwable rootCause, int statusCode, String code) {
		StringBuilder exceptionMessage = new StringBuilder();

		exceptionMessage
		.append(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
		exceptionMessage.append(StringUtils.SPACE);
		exceptionMessage.append(((UnrecognizedPropertyException) rootCause).getPropertyName());

		return new JMoneyErrorMessageDTO(exceptionMessage.toString(), statusCode);
	}

}
