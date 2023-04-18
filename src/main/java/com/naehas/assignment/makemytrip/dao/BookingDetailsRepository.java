package com.naehas.assignment.makemytrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naehas.assignment.makemytrip.entity.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

}
