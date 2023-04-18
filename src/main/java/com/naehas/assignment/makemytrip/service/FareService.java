package com.naehas.assignment.makemytrip.service;

import java.util.List;

import com.naehas.assignment.makemytrip.entity.FareDetails;


public interface FareService {

	List<FareDetails> findAllFares();

	FareDetails findFareById(int flightId);

	void deleteAll();

	FareDetails save(FareDetails fareDetails);

	void deleteById(int flightId);

}
