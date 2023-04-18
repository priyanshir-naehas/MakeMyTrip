package com.naehas.assignment.makemytrip.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "booking_details")
public class BookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	@SequenceGenerator(name = "my_seq_gen", sequenceName = "ENTITY_SEQ")
	private int bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user-id")
	private UserDetails user;

	@ManyToOne
	@JoinColumn(name = "fare_id")
	@JsonBackReference(value = "fare-details")
	private FareDetails fare;
	
	@Column(name = "booking_time")
	private LocalDateTime bookingTime;



	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public FareDetails getFare() {
		return fare;
	}

	public void setFare(FareDetails fare) {
		this.fare = fare;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	@PrePersist
	@PreUpdate
	public void setBookingTime() {
		this.bookingTime = LocalDateTime.now();
	}


	public BookingDetails(UserDetails user, FareDetails fare) {
		super();
		this.user = user;
		this.fare = fare;
	}

	public BookingDetails() {
		super();
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", user=" + user + ", fare=" + fare + ", bookingTime="
				+ bookingTime + "]";
	}


}
