package com.naehas.assignment.makemytrip.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<FlightErrorMessage> handleException(RuntimeException ex) {
		FlightErrorMessage errorResponse = new FlightErrorMessage();
		errorResponse.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}


	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {

		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);

	}

}
