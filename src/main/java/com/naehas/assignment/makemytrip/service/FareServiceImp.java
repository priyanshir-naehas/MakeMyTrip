package com.naehas.assignment.makemytrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naehas.assignment.makemytrip.dao.FareDetailsRepository;
import com.naehas.assignment.makemytrip.entity.FareDetails;

@Service
public class FareServiceImp implements FareService {

	@Autowired
	private FareDetailsRepository fareDetailsRepository;

	public FareServiceImp(FareDetailsRepository fareDetailsRepository) {
		super();
		this.fareDetailsRepository = fareDetailsRepository;
	}

	@Override
	public List<FareDetails> findAllFares() {
		return fareDetailsRepository.findAll();

	}

	@Override
	public FareDetails findFareById(int fareId) {

		return fareDetailsRepository.findById(fareId).orElse(null);

	}

	@Override
	public FareDetails save(FareDetails fareDetails) {
		return fareDetailsRepository.save(fareDetails);
	}

	@Override
	public void deleteById(int flightId) {
		fareDetailsRepository.deleteById(flightId);
	}

	@Override
	public void deleteAll() {
		fareDetailsRepository.deleteAll();
	}

}
