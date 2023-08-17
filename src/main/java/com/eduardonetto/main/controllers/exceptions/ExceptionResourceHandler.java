package com.eduardonetto.main.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eduardonetto.main.services.exceptions.BalanceException;
import com.eduardonetto.main.services.exceptions.DatabaseException;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;
import com.eduardonetto.main.services.exceptions.TransactionException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionResourceHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		String error = "Object not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<StandardError> transaction(TransactionException e, HttpServletRequest request) {
		String error = "Transaction exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(BalanceException.class)
	public ResponseEntity<StandardError> balance(BalanceException e, HttpServletRequest request) {
		String error = "Balance exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
