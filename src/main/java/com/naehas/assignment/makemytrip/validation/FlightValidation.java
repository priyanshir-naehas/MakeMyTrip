package com.naehas.assignment.makemytrip.validation;

import java.time.LocalDate;

public class FlightValidation {

	public void checkValid(LocalDate departureDate, LocalDate returnDate, String classType, String sortType,
			String departureShift)
			throws FlightNotFoundException {


		if (departureDate.isBefore(LocalDate.now())) {
			throw new FlightNotFoundException("Departure Date is incorrect! It must be before Current Date ");
		}

			if (departureDate.isAfter(returnDate)) {

				throw new FlightNotFoundException("Departure Date must be before Return Date ");

			}


			if (!(classType.equals("Economy") || classType.equals("Business"))) {
			throw new FlightNotFoundException("Incorrect class Type.Class Type can only be Economy or business");
		}

		if (!(sortType.equalsIgnoreCase("fare") || sortType.equalsIgnoreCase("duration")
				|| sortType.equalsIgnoreCase("null"))) {
			throw new FlightNotFoundException("Incorrect sort Type. Sort Type can only be Fare or Duration");
		}

		if (!(departureShift.equalsIgnoreCase("morning") || departureShift.equalsIgnoreCase("late")
				|| departureShift.equalsIgnoreCase("null"))) {
			throw new FlightNotFoundException(
					"Incorrect departure Shift . Departure Shift can only be Morning or Late");
		}

	}

	public void checkValid(LocalDate departureDate, String classType, String sortType, String departureShift) {

		if (departureDate.isBefore(LocalDate.now())) {
			throw new FlightNotFoundException("Departure Date is incorrect! It must be before Current Date ");
		}
		if (!(classType.equals("Economy") || classType.equals("Business"))) {
			throw new FlightNotFoundException("Incorrect class Type.Class Type can only be Economy or business");
		}

		if (!(sortType.equalsIgnoreCase("fare") || sortType.equalsIgnoreCase("duration")
				|| sortType.equalsIgnoreCase("null"))) {
			throw new FlightNotFoundException("Incorrect sort Type. Sort Type can only be Fare or Duration");
		}

		if (!(departureShift.equalsIgnoreCase("morning") || departureShift.equalsIgnoreCase("late")
				|| departureShift.equalsIgnoreCase("null"))) {
			throw new FlightNotFoundException(
					"Incorrect departure Shift . Departure Shift can only be Morning or Late");
		}


	}

	public void checkValid(String to, String from, LocalDate departureDate) {
		if (to.equals("null")) {
			throw new FlightNotFoundException("Parameter \'to\' is required");
		}
		if (from.equals("null")) {
			throw new FlightNotFoundException("Parameter \'from\' is required");
		}

		if (departureDate == null) {
			throw new FlightNotFoundException("Parameter \'departureDate\' is required");
		}

	}


}
