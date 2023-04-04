package com.naehas.assignment.makemytrip.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naehas.assignment.makemytrip.dao.FlightRepository;
import com.naehas.assignment.makemytrip.entity.Flight;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;

	public FlightServiceImpl(FlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}

	@Override
	public List<Flight> findAll() {
		return flightRepository.findAll();

	}

	@Override
	public Optional<Flight> findById(int flightId) {
		return flightRepository.findById(flightId);
	}

	@Override
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public void deleteById(int flightId) {
		flightRepository.deleteById(flightId);
	}

	@Override
	public void deleteAll() {
		flightRepository.deleteAll();
	}



	@Override
	public List<Object> searchFlights(String to, String from, LocalDate departureDate, String classType) {

		return flightRepository.findByToAndFromAndDepartureDateAndFareDetailsClassType(to, from, departureDate,
				classType);
	}



}
