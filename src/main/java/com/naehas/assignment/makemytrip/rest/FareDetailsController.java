package com.naehas.assignment.makemytrip.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naehas.assignment.makemytrip.entity.FareDetails;
import com.naehas.assignment.makemytrip.service.FareService;

@RestController
public class FareDetailsController {

	@Autowired
	private FareService fareService;

	public FareDetailsController(FareService fareService) {
		super();
		this.fareService = fareService;
	}

	@GetMapping("/fares")
	public List<FareDetails> getAllFares() {
		return fareService.findAllFares();
	}

	@PostMapping("fares")
	public FareDetails addFare(@RequestBody FareDetails fareDetails) {

		FareDetails fare_details = fareService.save(fareDetails);
		return fare_details;

	}

	@PutMapping("/fares")
	public FareDetails updateFare(@RequestBody FareDetails fareDetails) {

		FareDetails fare_details = fareService.save(fareDetails);
		return fare_details;


	}

	@DeleteMapping("/fares/{flightId}")
	public String deleteFare(@PathVariable int flightId) {

		FareDetails fareDetail = fareService.findFareById(flightId);
		fareService.deleteById(flightId);
		return "Deleted Fare Id" + flightId;

	}

	@DeleteMapping("/fares")
	public String deleteAllFares(FareDetails fareDetails) {

		fareService.deleteAll();
		return "Deleted flight";

	}

}
