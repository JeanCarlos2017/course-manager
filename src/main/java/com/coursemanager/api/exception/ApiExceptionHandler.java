package com.coursemanager.api.exception;

import java.time.OffsetDateTime;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;




@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CadastroException.class)
	public ResponseEntity<Object> handleErroCadastro(CadastroException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;

		var problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());
		problema.addCampo("mensagem", ex.getMessage());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(CadastroException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());
		problema.addCampo("mensagem", ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, 
	  WebRequest request) {
		Problema problema= new Problema();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        problema.addCampo(error.getField(), error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        problema.addCampo(error.getObjectName(), error.getDefaultMessage());
	    }
	    problema.setStatus(HttpStatus.BAD_REQUEST.value());
	    problema.setDataHora(OffsetDateTime.now());

	    return handleExceptionInternal(
	      ex, problema, headers, HttpStatus.BAD_REQUEST , request);
	}
}
