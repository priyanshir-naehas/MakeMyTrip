package com.naehas.assignment.makemytrip.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.naehas.assignment.makemytrip.entity.UserDetails;

public class AllBookingsDTO {


	private String airLine;
	private LocalDate departureDate;
	private LocalDate arrivalDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private String source;
	private String destination;
	private long duration;
	private String classType;
	private long fare;
	private UserDetails userDetails;


	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}



	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public AllBookingsDTO(String airLine, LocalDate departureDate, LocalDate arrivalDate,
			LocalTime departureTime, LocalTime arrivalTime, String source, String destination, long duration,
			String classType, long fare, UserDetails userDetails) {
		super();

		this.airLine = airLine;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.source = source;
		this.destination = destination;
		this.duration = duration;
		this.classType = classType;
		this.fare = fare;
		this.userDetails = userDetails;

	}



}
