package com.examly.springapploan.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CollegeApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collegeApplicationId;
    private LocalDateTime applicationDate;
    private String reasonForApplication;
    private String markSheet12th;
    private String percentage12th;
    private String school12th;
    @Column(name="application_status")
    private String status;
    private long userId;
    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;
 
}
