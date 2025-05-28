package com.examly.springapploan.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationRequest {
    private Long loanApplicationId;
    private User user;
    private Loan loan;
    private LocalDate applicationDate;
    private double loanAmount;
    private String loanPurpose;
    private String status;
    private String incomeProof;
}
