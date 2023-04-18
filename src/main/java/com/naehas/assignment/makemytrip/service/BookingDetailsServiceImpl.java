package com.naehas.assignment.makemytrip.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.naehas.assignment.makemytrip.dao.BookingDetailsRepository;
import com.naehas.assignment.makemytrip.dto.AllBookingsDTO;
import com.naehas.assignment.makemytrip.dto.BookingDetailsDTO;
import com.naehas.assignment.makemytrip.entity.BookingDetails;
import com.naehas.assignment.makemytrip.entity.FareDetails;
import com.naehas.assignment.makemytrip.entity.UserDetails;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	@Autowired
	BookingDetailsRepository bookingRepository;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	FareService fareService;

	public BookingDetailsServiceImpl(BookingDetailsRepository bookingRepository, UserDetailsService userDetailsService,
			FareService fareService) {
		super();
		this.bookingRepository = bookingRepository;
		this.userDetailsService = userDetailsService;
		this.fareService = fareService;
	}

	@Override
	public List<AllBookingsDTO> findAllBookings(Pageable page) {
		Page<BookingDetails> booking_details = bookingRepository.findAll(page);
		List<BookingDetails> booking = booking_details.getContent();
		if (booking.isEmpty()) {
			throw new FlightNotFoundException("Bookings Count :0");
		}

		return booking.stream()
				.map(bookingDetails -> new AllBookingsDTO(bookingDetails.getFare().getFlightNumber().getAirLine(),
						bookingDetails.getFare().getFlightNumber().getDepartureDate(),
						bookingDetails.getFare().getFlightNumber().getArrivalDate(),
						bookingDetails.getFare().getFlightNumber().getDepartureTime(),
						bookingDetails.getFare().getFlightNumber().getArrivalTime(),
						bookingDetails.getFare().getFlightNumber().getFrom(),
						bookingDetails.getFare().getFlightNumber().getTo(),
						bookingDetails.getFare().getFlightNumber().getDuration(),
						bookingDetails.getFare().getClassType(), bookingDetails.getFare().getFare(),
						bookingDetails.getUser()))
				.collect(Collectors.toList());
	}

//	@Override
//	public List<AllBookingsDTO> findAllBookings() {
//		List<BookingDetails> booking = bookingRepository.findAll();
//		if (booking.isEmpty()) {
//			throw new FlightNotFoundException("Bookings Count :0");
//		}
//         
//		return booking.stream()
//				.map(bookingDetails -> new AllBookingsDTO(
//						bookingDetails.getFare().getFlightNumber().getAirLine(),
//						bookingDetails.getFare().getFlightNumber().getDepartureDate(),
//						bookingDetails.getFare().getFlightNumber().getArrivalDate(),
//						bookingDetails.getFare().getFlightNumber().getDepartureTime(),
//						bookingDetails.getFare().getFlightNumber().getArrivalTime(),
//						bookingDetails.getFare().getFlightNumber().getFrom(),
//						bookingDetails.getFare().getFlightNumber().getTo(),
//						bookingDetails.getFare().getFlightNumber().getDuration(),
//						bookingDetails.getFare().getClassType(),
//						bookingDetails.getFare().getFare(),
//						bookingDetails.getUser()))
//						.collect(Collectors.toList());
//	}
	@Override
	public Optional<BookingDetails> findByBookingId(int bookingId) {
		return bookingRepository.findById(bookingId);
	}

	@Override
	public BookingDetails save(BookingDetails bookingDetails) {
		return bookingRepository.save(bookingDetails);
	}

	@Override
	public void deleteByBookingId(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	@Override
	public void deleteAllBookings() {
		bookingRepository.deleteAll();
	}

	@Override
	public void flightBooking(BookingDetailsDTO bookingDetailsDTO) {

		BookingDetails bookingDetails = new BookingDetails();

		UserDetails userDetails = new UserDetails();
		userDetails.setFirstName(bookingDetailsDTO.firstName());
		userDetails.setLastName(bookingDetailsDTO.lastName());
		userDetails.setPhoneNumber(bookingDetailsDTO.phoneNumber());
		userDetails.setGender(bookingDetailsDTO.gender());
		userDetails.setEmailId(bookingDetailsDTO.email());
		// userdetailservice.existsByEmailId
		if (userDetailsService.findByEmailId(bookingDetailsDTO.email()) != null) {
			UserDetails user_details = userDetailsService.findByEmailId(bookingDetailsDTO.email());
			bookingDetails.setUser(user_details);

		}

		else {
			UserDetails savedUser = userDetailsService.save(userDetails);
		bookingDetails.setUser(savedUser);
		}

		FareDetails fareDetails = fareService.findFareById(bookingDetailsDTO.fareId());

		if (fareDetails == null) {
			throw new RuntimeException("No Flight Found with the given Id");
		}

		bookingDetails.setFare(fareDetails);





		bookingRepository.save(bookingDetails);

	}

	

}
