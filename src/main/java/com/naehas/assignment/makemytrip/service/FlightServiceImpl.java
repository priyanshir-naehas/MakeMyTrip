package com.naehas.assignment.makemytrip.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.naehas.assignment.makemytrip.dao.FlightRepository;
import com.naehas.assignment.makemytrip.dto.FlightDTO;
import com.naehas.assignment.makemytrip.entity.FareDetails;
import com.naehas.assignment.makemytrip.entity.Flight;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;
import com.naehas.assignment.makemytrip.validation.FlightValidation;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;

	public FlightServiceImpl(FlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}

	@Override
	public List<FlightDTO> findAll(int pageNumber, int pageSize) {

		// EXCEPTIONS FOR PAGING in "GETFLIGHTS"
		if (pageNumber < 0) {
			throw new FlightNotFoundException("Page Number cannot be less than zero");
		}
		if (pageSize <= 0) {
			throw new FlightNotFoundException("Page Size cannot be less than or equal to zero");
		}

		Pageable page = PageRequest.of(pageNumber, pageSize);

		Page<Flight> pagedFlights = flightRepository.findAll(page);
		List<Flight> flights = pagedFlights.getContent();

		if (flights.isEmpty()) {
			throw new FlightNotFoundException("Flight Count :0");
		}
		return flights.stream()
				.map(flight -> new FlightDTO(flight.getAirLine(), flight.getDepartureTime(), flight.getArrivalTime(),
						flight.getFareDetails().stream().mapToLong(FareDetails::getFare), flight.getDuration(),
						flight.getTo(), flight.getFrom()))
				.collect(Collectors.toList());

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
	public List<FlightDTO> searchFlights(String to, String from, LocalDate departureDate, String classType,
			String sortType, String departureType, int pageNumber, int pageSize) {

		LocalTime startTime = null;
		LocalTime endTime = null;
		Pageable sortByDuration = null;
		Pageable sortByFares = null;

		// CHECKING FOR FILTER TYPE
		if (departureType.equalsIgnoreCase("morning")) {
			startTime = LocalTime.of(5, 0);
			endTime = LocalTime.of(12, 0);
		} else if (departureType.equalsIgnoreCase("late")) {
			startTime = LocalTime.of(18, 0);
			endTime = LocalTime.of(23, 59);
		}

		// CHECKING FOR SORT TYPE
		if (sortType.equalsIgnoreCase("duration")) {
			sortByDuration = PageRequest.of(pageNumber, pageSize, Sort.by("duration"));
		}

		else if (sortType.equalsIgnoreCase("fare")) {
			sortByFares = PageRequest.of(pageNumber, pageSize, Sort.by("fareDetails.fare"));
		}

		// variable to store the field for which we have to sort data along with paging
		Pageable sendSort = null;

		// Logic to sort
		if (sortByDuration != null) {
			sendSort = sortByDuration;
		} else if (sortByFares != null) {
			sendSort = sortByFares;
		} else {
			// Normal Paging if Sort Type is NULL
			sendSort = PageRequest.of(pageNumber, pageSize);
		}

		List<Flight> flights = new ArrayList<>();

		// FOR MORNING DEPARTURE AND LATE DEPARTURE
		if (departureType.equalsIgnoreCase("morning") || departureType.equalsIgnoreCase("late")) {
			flights = flightRepository.findByToAndFromAndDepartureDateAndDepartureTimeBetween(to, from, departureDate,
					startTime, endTime, sendSort);
		} else {
			flights = flightRepository.findByToAndFromAndDepartureDate(to, from, departureDate, sendSort);
		}

		for (Flight flight : flights) {
			flight.getFareDetails().removeIf(fareDetail -> !fareDetail.getClassType().equals(classType));
		}

		return flights.stream()
				.map(flight -> new FlightDTO(flight.getAirLine(), flight.getDepartureTime(), flight.getArrivalTime(),
						flight.getFareDetails().stream().mapToLong(FareDetails::getFare), flight.getDuration(),
						flight.getTo(), flight.getFrom()))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlightDTO> searchFlights(String to, String from, LocalDate departureDate, String classType,
			String roundTrip, LocalDate returnDate, String sortType, String departureType, int pageNumber,
			int pageSize) {

		List<FlightDTO> oneWayTripFlights = new ArrayList<>();
		classType = classType.toLowerCase();
		classType = StringUtils.capitalize(classType);

		// VALIDATION
		FlightValidation flightValidation = new FlightValidation();
		flightValidation.checkValid(to, from, departureDate, pageNumber, pageSize);

		// SORTING FOR ONEWAY
		oneWayTripFlights = searchFlights(to, from, departureDate, classType, sortType, departureType,
				pageNumber, pageSize);

		// SORTING FOR ROUNDTRIP
		if (roundTrip.equalsIgnoreCase("true")) {
			List<FlightDTO> roundTripFlights = new ArrayList<>();

			// VALIDATION
			flightValidation.checkValid(departureDate, returnDate, classType, sortType, departureType);

			roundTripFlights = searchFlights(from, to, returnDate, classType, sortType, departureType,
					pageNumber, pageSize);

			oneWayTripFlights.addAll(roundTripFlights);

		} else if (roundTrip.equalsIgnoreCase("false")) {

			// VALIDATION
			flightValidation.checkValid(departureDate, classType, sortType, departureType);
			if (oneWayTripFlights.isEmpty()) {
				throw new FlightNotFoundException("No Flight present!!");
			}
			return oneWayTripFlights;
		}

		else {
			throw new FlightNotFoundException("Incorrect roundTrip Type . Accepted values : True or False");
		}

		if (oneWayTripFlights.isEmpty()) {
			throw new FlightNotFoundException("No Flight present!!");
		}

		return oneWayTripFlights;
	}

}
