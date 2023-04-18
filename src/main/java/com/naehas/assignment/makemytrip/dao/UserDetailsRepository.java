package com.naehas.assignment.makemytrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naehas.assignment.makemytrip.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByEmailId(String emailId);


}
