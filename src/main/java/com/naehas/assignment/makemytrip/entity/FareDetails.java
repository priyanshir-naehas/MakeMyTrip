package com.naehas.assignment.makemytrip.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "fare_details")

public class FareDetails {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fare")
	private long fare;

	@Column(name = "class_type")
	public String classType;

	@Column(name = "flight_number", insertable = false, updatable = false)
	private int flightNumber;

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}


	public FareDetails(long fare) {
		super();
		this.fare = fare;
	}

	public FareDetails(long fare, String classType, int flightNumber) {
		super();
		this.fare = fare;
		this.classType = classType;
		this.flightNumber = flightNumber;

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public FareDetails() {
		super();
	}

	@Override
	public String toString() {
		return "FareDetails [id=" + id + ", fare=" + fare + ", classType=" + classType + ", flightNumber="
				+ flightNumber + "]";
	}

}
