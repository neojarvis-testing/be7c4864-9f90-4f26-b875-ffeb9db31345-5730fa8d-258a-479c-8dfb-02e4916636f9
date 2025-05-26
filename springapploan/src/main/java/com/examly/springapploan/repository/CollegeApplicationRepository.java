package com.examly.springapploan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapploan.model.CollegeApplication;
@Repository
public interface CollegeApplicationRepository extends JpaRepository<CollegeApplication,Integer> {
    
}
