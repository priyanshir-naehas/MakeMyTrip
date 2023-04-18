package com.naehas.assignment.makemytrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.naehas.assignment.makemytrip.dto.AllBookingsDTO;
import com.naehas.assignment.makemytrip.dto.BookingDetailsDTO;
import com.naehas.assignment.makemytrip.entity.BookingDetails;

public interface BookingDetailsService {



	Optional<BookingDetails> findByBookingId(int bookingId);

	BookingDetails save(BookingDetails bookingDetails);

	void deleteByBookingId(int bookingId);

	void deleteAllBookings();

	public void flightBooking(BookingDetailsDTO bookingDetails);

	List<AllBookingsDTO> findAllBookings(Pageable page);

}
