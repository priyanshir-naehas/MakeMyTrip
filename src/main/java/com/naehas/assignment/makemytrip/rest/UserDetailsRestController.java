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

import com.naehas.assignment.makemytrip.entity.UserDetails;
import com.naehas.assignment.makemytrip.service.UserDetailsService;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;

@RestController
public class UserDetailsRestController {

	@Autowired
	private UserDetailsService userDetailsService;

	// CONSTRUCTOR
	public UserDetailsRestController(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	// Get All Users With Paging
	@GetMapping("/users")
	public List<UserDetails> getAllUsers(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

		// EXCEPTIONS FOR PAGING in "GETUSERS"
		if (pageNumber < 0) {
			throw new FlightNotFoundException("Page Number cannot be less than zero");
		}
		if (pageSize <= 0) {
			throw new FlightNotFoundException("Page Size cannot be less than or equal to zero");
		}

		Pageable page = PageRequest.of(pageNumber, pageSize);
		return userDetailsService.findAllUsers(page);
	}


	@GetMapping("/users/{bookingId}")
	public Optional<UserDetails> getUserById(@PathVariable int bookingId) {

		Optional<UserDetails> userById = userDetailsService.findByUserId(bookingId);

		if (!userById.isPresent()) {
			throw new FlightNotFoundException("User Not Found with Booking Id :" + bookingId);
		}

		return userById;

	}

	// Add a new User
	@PostMapping(value = "/users")
	public UserDetails addUser(@RequestBody UserDetails userDetails) {

		UserDetails user_details = userDetailsService.save(userDetails);
		return user_details;

	}

	// Updating Users
	@PutMapping("/users")
	public UserDetails updateUser(@RequestBody UserDetails userDetails) {

		UserDetails user_details = userDetailsService.save(userDetails);
		return user_details;

	}

	// Deleting User By BookingId
	@DeleteMapping("/users/{bookingId}")
	public String deleteFlight(@PathVariable int bookingId) {

		Optional<UserDetails> userDetail = userDetailsService.findByUserId(bookingId);
		userDetailsService.deleteByUserId(bookingId);
		return "Deleted User with Booking Id :" + bookingId;

	}

	// Deleting all Flights
	@DeleteMapping("/users")
	public String deleteAllUsers(UserDetails userDetails) {

		userDetailsService.deleteAllUsers();
		return "Deleted User";

	}

}
