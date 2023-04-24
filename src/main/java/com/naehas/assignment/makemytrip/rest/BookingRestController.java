package com.naehas.assignment.makemytrip.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naehas.assignment.makemytrip.dto.AllBookingsDTO;
import com.naehas.assignment.makemytrip.dto.BookingDetailsDTO;
import com.naehas.assignment.makemytrip.entity.BookingDetails;
import com.naehas.assignment.makemytrip.service.BookingDetailsService;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;

@RestController
@RequestMapping("bookings")
public class BookingRestController {

	@Autowired
	private BookingDetailsService bookingService;


	// CONSTRUCTOR
	public BookingRestController(BookingDetailsService bookingService) {
		super();
		this.bookingService = bookingService;
	}


	// Get All Booking along with a specific flightNumber With Paging
	@GetMapping("")
	public List<AllBookingsDTO> getAllBookings(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "0") int flightNumber,
			@RequestParam(required = false) String emailId,
			@RequestParam(required = false, defaultValue = "null") String sortType) {

		List<AllBookingsDTO> bookinglist = bookingService.findAllBookings(pageNumber, pageSize, flightNumber, emailId,
				sortType);
		return bookinglist;
	}



	@GetMapping("/{bookingId}")
	public Optional<BookingDetails> getUserById(@PathVariable int bookingId) {

		Optional<BookingDetails> bookById = bookingService.findByBookingId(bookingId);

		if (!bookById.isPresent()) {
			throw new FlightNotFoundException("User Not Found with Booking Id :" + bookingId);
		}

		return bookById;

	}

	// Add a new Booking
	@PostMapping(value = "")
	public String addBooking(@RequestBody BookingDetailsDTO bookingDetails) {

		bookingService.flightBooking(bookingDetails);
		return "Booking saved!!";

	}

	// Updating Booking
	@PutMapping("")
	public BookingDetails updateBooking(@RequestBody BookingDetails bookingDetails) {

		BookingDetails user_details = bookingService.save(bookingDetails);
		return user_details;

	}

	// Deleting Booking By BookingId
	@DeleteMapping("/{bookingId}")
	public String deleteFlightBooking(@PathVariable int bookingId) {

		bookingService.deleteByBookingId(bookingId);
		return "Deleted Booking with Booking Id :" + bookingId;

	}

	// Deleting all bookings
	@DeleteMapping("")
	public String deleteAllUsers(BookingDetails bookingDetails) {

		bookingService.deleteAllBookings();
		return "Deleted Booking";

	}

}
