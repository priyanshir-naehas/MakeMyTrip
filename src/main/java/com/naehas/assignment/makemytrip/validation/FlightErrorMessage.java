package com.naehas.assignment.makemytrip.validation;

public class FlightErrorMessage {

	private String message;
	private int status;
	private long timeStamp;

	public FlightErrorMessage(String message, int status, long timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp = timeStamp;
	}

	public FlightErrorMessage() {
		super();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long l) {
		this.timeStamp = l;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
