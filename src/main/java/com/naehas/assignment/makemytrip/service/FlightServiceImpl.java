package com.naehas.assignment.makemytrip.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	public List<Flight> searchFlights(String to, String from, LocalDate departureDate, String classType,
			String sortType, String filterType) {

		LocalTime startTime = null;
		LocalTime endTime = null;

		if (filterType.equals("Morning Departure")) {
			startTime = LocalTime.of(5, 0);
			endTime = LocalTime.of(12, 0);
		} else if (filterType.equals("Late Departure")) {
			startTime = LocalTime.of(18, 0);
			endTime = LocalTime.of(23, 59);
		}

		Sort sortByDuration = null;
		Sort sortByFares = null;

		if (sortType.equals("Duration")) {
			sortByDuration = Sort.by("duration");
		}

		else if (sortType.equals("Fare")) {
			sortByFares = Sort.by("fareDetails.fare");
		}

		// variable to store the field for which we have to filter data
		Sort sendFilter = null;

		// Logic to filter
		if (sortByDuration != null) {
			sendFilter = sortByDuration;
		} else if (sortByFares != null) {
			sendFilter = sortByFares;
		}

		List<Flight> flights = new ArrayList<>();


		// FOR MORNING DEPARTURE AND LATE DEPARTURE

		if (!filterType.equals("Morning Departure") && !filterType.equals("Late Departure")) {
			flights = flightRepository.findByToAndFromAndDepartureDate(to, from, departureDate, sendFilter);
		} else {
			flights = flightRepository.findByToAndFromAndDepartureDateAndDepartureTimeBetween(to, from, departureDate,
					sendFilter, startTime, endTime);
		}

		for (Flight flight : flights) {
			flight.getFareDetails().removeIf(fareDetail -> !fareDetail.getClassType().equals(classType));
		}

		return flights;
	}
}
