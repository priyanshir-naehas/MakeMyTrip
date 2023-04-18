package com.naehas.assignment.makemytrip.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naehas.assignment.makemytrip.dto.AllBookingsDTO;
import com.naehas.assignment.makemytrip.dto.BookingDetailsDTO;
import com.naehas.assignment.makemytrip.entity.BookingDetails;
import com.naehas.assignment.makemytrip.service.BookingDetailsService;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;

@RestController
public class BookingRestController {

	@Autowired
	private BookingDetailsService bookingService;


	// CONSTRUCTOR
	public BookingRestController(BookingDetailsService bookingService) {
		super();
		this.bookingService = bookingService;
	}


	// Get All Booking along with a specific flightNumber With Paging
	@GetMapping("/booking")
	public List<AllBookingsDTO> getAllBookings(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "0") int flightNumber,
			@RequestParam(required = false) String emailId) {

		// EXCEPTIONS FOR PAGING in "GETUSERS"
		if (pageNumber < 0) {
			throw new FlightNotFoundException("Page Number cannot be less than zero");
		}
		if (pageSize <= 0) {
			throw new FlightNotFoundException("Page Size cannot be less than or equal to zero");
		}

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<AllBookingsDTO> bookinglist = bookingService.findAllBookings(page, flightNumber, emailId);
		return bookinglist;
	}



	@GetMapping("/booking/{bookingId}")
	public Optional<BookingDetails> getUserById(@PathVariable int bookingId) {

		Optional<BookingDetails> bookById = bookingService.findByBookingId(bookingId);

		if (!bookById.isPresent()) {
			throw new FlightNotFoundException("User Not Found with Booking Id :" + bookingId);
		}

		return bookById;

	}

	// Add a new Booking
	@PostMapping(value = "/booking")
	public String addBooking(@RequestBody BookingDetailsDTO bookingDetails) {

		bookingService.flightBooking(bookingDetails);
		return "Booking saved!!";

	}

	// Updating Booking
	@PutMapping("/booking")
	public BookingDetails updateBooking(@RequestBody BookingDetails bookingDetails) {

		BookingDetails user_details = bookingService.save(bookingDetails);
		return user_details;

	}

	// Deleting Booking By BookingId
	@DeleteMapping("/booking/{bookingId}")
	public String deleteFlight(@PathVariable int bookingId) {

		Optional<BookingDetails> userDetail = bookingService.findByBookingId(bookingId);
		bookingService.deleteByBookingId(bookingId);
		return "Deleted Booking with Booking Id :" + bookingId;

	}

	// Deleting all bookings
	@DeleteMapping("/booking")
	public String deleteAllUsers(BookingDetails bookingDetails) {

		bookingService.deleteAllBookings();
		return "Deleted Booking";

	}

}
