package com.naehas.assignment.makemytrip.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.naehas.assignment.makemytrip.entity.Flight;

public interface FlightService {

	public List<Flight> findAll();

	public Optional<Flight> findById(int flightId);

	public Flight save(Flight flight);

	public void deleteById(int flightId);

	public void deleteAll();

	List<Flight> searchFlights(String to, String from, LocalDate departureDate, String classType, boolean sortDuration,
			boolean sortFares);

}
