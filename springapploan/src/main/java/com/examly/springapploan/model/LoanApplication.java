package com.examly.springapploan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanApplicationId;
    private LocalDate applicationDate;
    private double loanAmount;
    private int tenureMonths;
    private String applicationStatus;
    private String employmentStatus;
    private double annualIncome;
    private String remarks;
    private String proof;
    private String accountHolder;
    private String accountNumber;
    private String iFSCCode;
    private long userId;
    private Loan loan;
}
