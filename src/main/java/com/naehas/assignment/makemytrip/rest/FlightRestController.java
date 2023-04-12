package com.naehas.assignment.makemytrip.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naehas.assignment.makemytrip.dto.FlightDTO;
import com.naehas.assignment.makemytrip.entity.Flight;
import com.naehas.assignment.makemytrip.service.FlightService;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;
import com.naehas.assignment.makemytrip.validation.FlightValidation;

@RestController
public class FlightRestController {

	@Autowired
	private FlightService flightService;

	public FlightRestController(FlightService flightService) {
		super();
		this.flightService = flightService;
	}

	// Get All Flights
	@GetMapping("/flights")
	public List<Flight> getAllFlights(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return flightService.findAll(page);
	}

	@GetMapping("/flights/{flightId}")
	public Optional<Flight> getFlightDetail(@PathVariable int flightId) {

		Optional<Flight> flightById = flightService.findById(flightId);

		if (!flightById.isPresent()) {
			throw new FlightNotFoundException("Flight Not Found with Id :" + flightId);
		}

		return flightById;

	}

	// Add a new Flight
	@PostMapping("flights")
	public Flight addFlight(@RequestBody Flight flight) {

		Flight flight_detail = flightService.save(flight);
		return flight_detail;

	}

	// Updating Flights
	@PutMapping("/flights")
	public Flight updateFlight(@RequestBody Flight flight) {

		Flight flight_detail = flightService.save(flight);
		return flight_detail;

	}

	// Deleting Flight By Id
	@DeleteMapping("/flights/{flightId}")
	public String deleteFlight(@PathVariable int flightId) {

		Optional<Flight> flightDetail = flightService.findById(flightId);
		flightService.deleteById(flightId);
		return "Deleted flight Id :" + flightId;

	}

	// Deleting all Flights
	@DeleteMapping("/flights")
	public String deleteAllFlights(Flight flight) {

		flightService.deleteAll();
		return "Deleted flight";

	}

	// Searching,Sorting,Filtering Flights
	@GetMapping("/search")
	public List<FlightDTO> searchFlights(@RequestParam(value = "to", defaultValue = "null") String to,
			@RequestParam(value = "from", defaultValue = "null") String from,
			@RequestParam(value = "departureDate", required = false) LocalDate departureDate,
			@RequestParam("classType") String classType, @RequestParam("roundTrip") String roundTrip,
			@RequestParam(value = "returnDate", required = false) LocalDate returnDate,
			@RequestParam(value = "sortType", defaultValue = "null", required = false) String sortType,
			@RequestParam(value = "departureType", required = false, defaultValue = "null") String departureType,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize)
			throws Exception {


		List<FlightDTO> oneWayTripFlights = new ArrayList<>();
		classType = classType.toLowerCase();
		classType = StringUtils.capitalize(classType);

		// VALIDATION
		FlightValidation flightValidation = new FlightValidation();
		flightValidation.checkValid(to, from, departureDate);

		// SORTING FOR ONEWAY
		oneWayTripFlights = flightService.searchFlights(to, from, departureDate, classType, sortType, departureType,
				pageNumber, pageSize);

		// SORTING FOR ROUNDTRIP
		if (roundTrip.equalsIgnoreCase("true")) {
			List<FlightDTO> roundTripFlights = new ArrayList<>();

			// VALIDATION
			flightValidation.checkValid(departureDate, returnDate, classType, sortType, departureType);

			roundTripFlights = flightService.searchFlights(from, to, returnDate, classType, sortType, departureType,
					pageNumber, pageSize);

			oneWayTripFlights.addAll(roundTripFlights);

		} else if (roundTrip.equalsIgnoreCase("false")) {

			// VALIDATION
			flightValidation.checkValid(departureDate, classType, sortType, departureType);
			if (oneWayTripFlights.isEmpty()) {
				throw new FlightNotFoundException("No Flight present!!");
			}
			return oneWayTripFlights;
		} else {
			throw new FlightNotFoundException("Incorrect roundTrip Type . Accepted values : True or False");
		}


		if (oneWayTripFlights.isEmpty()) {
			throw new FlightNotFoundException("No Flight present!!");
		}

		return oneWayTripFlights;
	}

}
