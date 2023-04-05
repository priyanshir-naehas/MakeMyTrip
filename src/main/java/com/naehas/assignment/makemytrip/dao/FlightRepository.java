package com.naehas.assignment.makemytrip.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naehas.assignment.makemytrip.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	//@Query("Select f.flightId, f.duration, f.airLine,f.arrivalDate,f.departureDate,f.departureTime,f.arrivalTime,f.from,f.to,fd.classType, fd.fare from Flight f, FareDetails fd where f.flightId=fd.flightNumber and f.to=:to and f.from=:from and f.departureDate=:departureDate and  fd.classType=:classType")
	List<Flight> findByToAndFromAndDepartureDate(String to, String from, LocalDate departureDate, Sort sort);


}
