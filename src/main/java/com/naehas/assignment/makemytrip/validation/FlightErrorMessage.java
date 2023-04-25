package com.naehas.assignment.makemytrip.validation;

public class FlightErrorMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FlightErrorMessage(String message) {
		super();
		this.message = message;
	}

	public FlightErrorMessage() {
		super();
	}

}