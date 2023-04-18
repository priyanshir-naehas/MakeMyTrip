package com.naehas.assignment.makemytrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.naehas.assignment.makemytrip.entity.UserDetails;

public interface UserDetailsService {

	List<UserDetails> findAllUsers(Pageable page);



	UserDetails save(UserDetails userDetails);

	void deleteAllUsers();

	Optional<UserDetails> findByUserId(int userId);

	void deleteByUserId(int userId);

	UserDetails findByEmailId(String emailId);

}
