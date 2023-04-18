package com.naehas.assignment.makemytrip.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naehas.assignment.makemytrip.entity.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

	Page<BookingDetails> findByFareFlightNumberFlightId(Pageable page, int flightId);

	Page<BookingDetails> findByUserEmailId(Pageable page, String emailId);
}
