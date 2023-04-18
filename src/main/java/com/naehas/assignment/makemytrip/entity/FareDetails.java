package com.naehas.assignment.makemytrip.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


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
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fare")
	@JsonManagedReference(value = "fare-details")
	private List<BookingDetails> userDetails;

    //Getters and Setters
	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
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

	public List<BookingDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<BookingDetails> userDetails) {
		this.userDetails = userDetails;
	}


	public FareDetails() {
		super();
	}

	// Constructor

	@Override
	public String toString() {
		return "FareDetails [id=" + id + ", fare=" + fare + ", classType=" + classType + ", flightNumber="
				+ flightNumber + "]";
	}

	public FareDetails(long fare, String classType, Flight flightNumber, List<BookingDetails> userDetails) {
		super();
		this.fare = fare;
		this.classType = classType;
		this.flightNumber = flightNumber;
		this.userDetails = userDetails;
	}



}
