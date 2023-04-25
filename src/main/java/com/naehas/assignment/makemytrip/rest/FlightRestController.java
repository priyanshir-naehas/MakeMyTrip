package com.naehas.assignment.makemytrip.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naehas.assignment.makemytrip.dto.FlightDTO;
import com.naehas.assignment.makemytrip.entity.Flight;
import com.naehas.assignment.makemytrip.service.FlightService;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("flights")
public class FlightRestController {

	@Autowired
	private FlightService flightService;


	public FlightRestController(FlightService flightService) {
		super();
		this.flightService = flightService;
	}


	// Get All Flights With Paging
	@GetMapping("")
	public List<FlightDTO> getAllFlights(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

		return flightService.findAll(pageNumber, pageSize);
	}


	@GetMapping("/{flightId}")
	public Optional<Flight> getFlightDetail(@PathVariable int flightId) {

		Optional<Flight> flightById = flightService.findById(flightId);

		if (!flightById.isPresent()) {
			throw new FlightNotFoundException("Flight Not Found with Id :" + flightId);
		}

		return flightById;

	}

	// Add a new Flight
	@PostMapping("")
	public Flight addFlight(@Valid @RequestBody Flight flight) {

		Flight flight_detail = flightService.save(flight);
		return flight_detail;

	}

	// Updating Flights
	@PutMapping("")
	public Flight updateFlight(@RequestBody Flight flight) {

		Flight flight_detail = flightService.save(flight);
		return flight_detail;

	}

	// Deleting Flight By Id
	@DeleteMapping("/{flightId}")
	public String deleteFlight(@PathVariable int flightId) {

		Optional<Flight> flightDetail = flightService.findById(flightId);
		flightService.deleteById(flightId);
		return "Deleted flight Id :" + flightId;

	}

	// Deleting all Flights
	@DeleteMapping("")
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
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) throws Exception {

		
		List<FlightDTO>findFlights=flightService.searchFlights(to,from,departureDate,classType,roundTrip,returnDate,sortType,departureType,pageNumber,pageSize);
		
		return findFlights;
}
}

