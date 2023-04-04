package com.naehas.assignment.makemytrip.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.naehas.assignment.makemytrip.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Serializable> {

	@Query("Select f.flightId, f.duration, f.airLine, f.arrivalDate,f.departureDate,f.departureTime,f.arrivalTime,f.from,f.to, fd.classType, fd.fare from Flight f, FareDetails fd where f.flightId=fd.flightNumber and f.to=?1 and f.from=?2 and f.departureDate=?3 and fd.classType=?4 ORDER BY f.duration")
	List<Object> findByToAndFromAndDepartureDateAndClassTypeOrderByDurationAsc(@Param("to") String to,
			@Param("from") String from,
			@Param("departureDate") LocalDate departureDate, @Param("classType") String classType);

//	List<Object> findAndSortByDuration(@Param("to") String to, @Param("from") String from,
//			@Param("departureDate") LocalDate departureDate, @Param("classType") String classType);

}
