package com.examly.springapploan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapploan.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College,Integer> {
    
}
