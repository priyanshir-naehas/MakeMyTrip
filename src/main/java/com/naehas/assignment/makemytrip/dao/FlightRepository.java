package com.naehas.assignment.makemytrip.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naehas.assignment.makemytrip.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {


	List<Flight> findByToAndFromAndDepartureDate(String to, String from, LocalDate departureDate, Pageable sendSort);

	List<Flight> findByToAndFromAndDepartureDateAndDepartureTimeBetween(String to, String from,
			LocalDate departureDate,
			LocalTime begin, LocalTime end, Pageable sendSort);


}
