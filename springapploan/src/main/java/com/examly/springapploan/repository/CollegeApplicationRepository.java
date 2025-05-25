package com.examly.springapploan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapploan.model.CollegeApplication;

public interface CollegeApplicationRepository extends JpaRepository<CollegeApplication,Integer> {
    
}
