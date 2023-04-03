package com.naehas.assignment.makemytrip.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naehas.assignment.makemytrip.entity.FareDetails;

@Repository
public interface FareDetailsRepository extends JpaRepository<FareDetails, Serializable> {


}
