package com.naehas.assignment.makemytrip.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException 
{



	public FlightNotFoundException(String message) {
		super(message);

	}

}
