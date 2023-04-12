package com.naehas.assignment.makemytrip.dto;

import java.time.LocalTime;
import java.util.stream.LongStream;

public class FlightDTO {

	private String airLine;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LongStream fare;
	private long duration;
	private String from;
	private String to;



	public FlightDTO(String airLine, LocalTime departureTime, LocalTime arrivalTime, LongStream fare, long duration,
			String to, String from) {
		super();
		this.airLine = airLine;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.duration = duration;
		this.from = from;
		this.to = to;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LongStream getFare() {
		return fare;
	}

	public void setFare(LongStream longStream) {
		this.fare = longStream;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
