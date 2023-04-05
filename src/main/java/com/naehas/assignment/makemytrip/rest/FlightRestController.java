package com.naehas.assignment.makemytrip.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naehas.assignment.makemytrip.entity.Flight;
import com.naehas.assignment.makemytrip.service.FlightService;

@RestController
public class FlightRestController {

	@Autowired
	private FlightService flightService;

	public FlightRestController(FlightService flightService) {
		super();
		this.flightService = flightService;
	}

	@GetMapping("/flights")
	public List<Flight> getAllFlights() {
		return flightService.findAll();
	}

	@PostMapping("flights")
	public Flight addFlight(@RequestBody Flight flight) {

		Flight flight_detail = flightService.save(flight);
		return flight_detail;

	}

	@PutMapping("/flights")
	public Flight updateFlight(@RequestBody Flight flight) {

		Flight flight_detail = flightService.save(flight);
		return flight_detail;

	}

	@DeleteMapping("/flights/{flightId}")
	public String deleteFlight(@PathVariable int flightId) {

		Optional<Flight> flightDetail = flightService.findById(flightId);
		flightService.deleteById(flightId);
		return "Deleted flight Id :" + flightId;

	}

	@DeleteMapping("/flights")
	public String deleteAllFlights(Flight flight) {

		flightService.deleteAll();
		return "Deleted flight";

	}

	@GetMapping("/search")
	public List<Flight> searchFlights(@RequestParam("to") String to, @RequestParam("from") String from,
			@RequestParam("departureDate") LocalDate departureDate, @RequestParam("classType") String classType,
			@RequestParam("roundTrip") boolean roundTrip,
			@RequestParam(value = "returnDate", required = false) LocalDate returnDate,
			@RequestParam(value = "sortByFilter", required = false) String sortByFilter) {

		List<Flight> oneWayTripFlights = new ArrayList<>();

		// SORTING FOR ONEWAY
		if (sortByFilter.equals("Duration")) {
			// if we sort by duration and sort by fares
				oneWayTripFlights = flightService.searchFlights(to, from, departureDate, classType, true, false);
			}
			else if (sortByFilter.equals("Fare")) {
				oneWayTripFlights = flightService.searchFlights(to, from, departureDate, classType, false, true);

			}

		// SORTING FOR ROUNDTRIP
		if (roundTrip == true) {
			List<Flight> roundTripFlights = new ArrayList<>();

			if (sortByFilter.equals("Duration")) {
				// if we sort by duration and not by fares
					roundTripFlights = flightService.searchFlights(from, to, returnDate, classType, true, false);
				}
				else if (sortByFilter.equals("Fare")) {
					roundTripFlights = flightService.searchFlights(from, to, returnDate, classType, false, true);

				}

			oneWayTripFlights.addAll(roundTripFlights);

		} else {
			return oneWayTripFlights;
		}

		return oneWayTripFlights;
	}

}
