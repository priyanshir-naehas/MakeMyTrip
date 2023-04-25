package com.naehas.assignment.makemytrip.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookingDetailsDTO(
		@NotEmpty(message = "Required field : First Name") @Size(min = 3, message = "First name should always be greater than 3") String firstName,
		@NotEmpty(message = "Required field : Last Name") @Size(min = 3, message = "Last name should always be greater than 3") String lastName,
		@NotNull(message = "Required field : Phone Number") long phoneNumber,
		@Email(message = "Enter valid Email Address ") String email,
		@NotEmpty(message = "Required field : Gender") String gender,
		int fareId) {

}
