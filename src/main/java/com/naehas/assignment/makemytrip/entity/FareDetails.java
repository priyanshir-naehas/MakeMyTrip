package com.naehas.assignment.makemytrip.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "flight_number", nullable = false)
	@JsonBackReference
	private Flight flightNumber;

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

	public FareDetails(long fare, String classType, Flight flightNumber) {
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

	public Flight getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Flight flightNumber) {
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
