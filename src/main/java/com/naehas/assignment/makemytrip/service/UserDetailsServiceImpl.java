package com.naehas.assignment.makemytrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.naehas.assignment.makemytrip.dao.UserDetailsRepository;
import com.naehas.assignment.makemytrip.entity.UserDetails;
import com.naehas.assignment.makemytrip.validation.FlightNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
		super();
		this.userDetailsRepository = userDetailsRepository;
	}


	@Override
	public List<UserDetails> findAllUsers(Pageable page) {

		Page<UserDetails> userDetails = userDetailsRepository.findAll(page);
		List<UserDetails> users = userDetails.getContent();
		if (users.isEmpty()) {
			throw new FlightNotFoundException("Users Count :0");
		}
		return users;

	}

	@Override
	public Optional<UserDetails> findByUserId(int userId) {
		return userDetailsRepository.findById(userId);
	}

	@Override
	public UserDetails save(UserDetails userDetails) {
		return userDetailsRepository.save(userDetails);
	}

	@Override
	public void deleteByUserId(int userId) {
		userDetailsRepository.deleteById(userId);
	}

	@Override
	public void deleteAllUsers() {
		userDetailsRepository.deleteAll();
	}

	@Override
	public UserDetails findByEmailId(String emailId) {
		return userDetailsRepository.findByEmailId(emailId);

	}

}
