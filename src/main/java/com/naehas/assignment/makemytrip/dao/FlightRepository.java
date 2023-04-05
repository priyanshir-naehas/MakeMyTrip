package com.naehas.assignment.makemytrip.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naehas.assignment.makemytrip.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	List<Flight> findByToAndFromAndDepartureDate(String to, String from, LocalDate departureDate, Sort sort);


}
