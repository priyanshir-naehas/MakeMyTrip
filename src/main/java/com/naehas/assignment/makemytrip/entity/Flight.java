package com.naehas.assignment.makemytrip.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Component
@Entity
@Table(name = "flight_data")

public class Flight {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	@SequenceGenerator(name = "my_seq_gen", sequenceName = "ENTITY_SEQ")
	private int flightId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_number", referencedColumnName = "flight_id")
	private List<FareDetails> fareDetails;

	@Column(name = "airline")
	private String airLine;



	@Column(name = "from_location")
	private String from;



	@Column(name = "to_location")
	private String to;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dept_date", nullable = false)
	private LocalDate departureDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "arr_date", nullable = true)
	private LocalDate arrivalDate;

	@Column(name = "dept_time", nullable = false)
	private LocalTime departureTime;

	@Column(name = "arr_time", nullable = true)
	private LocalTime arrivalTime;



	@Column(name = "duration")
	private long duration;


	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}


	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
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

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public long getDuration() {
		LocalDateTime dept_date = LocalDateTime.of(departureDate, departureTime);
		LocalDateTime arr_date = LocalDateTime.of(arrivalDate, arrivalTime);

		long duration = Duration.between(dept_date, arr_date).toMinutes();
		return duration;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public List<FareDetails> getFareDetails() {
		return fareDetails;
	}

	public void setFareDetails(List<FareDetails> fareDetails) {
		this.fareDetails = fareDetails;
	}

	@PrePersist
	@PreUpdate
	public void setDuration() {
		this.duration = getDuration();
	}

	public Flight(List<FareDetails> fareDetails, String airLine, String from, String to, LocalDate departureDate,
			LocalDate arrivalDate, LocalTime departureTime, LocalTime arrivalTime) {
		super();
		this.fareDetails = fareDetails;
		this.airLine = airLine;
		this.from = from;
		this.to = to;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;


	}


	public Flight() {
		super();
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", fareDetails=" + fareDetails + ", airLine=" + airLine + ", from="
				+ from + ", to=" + to + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + "]";
	}





}
