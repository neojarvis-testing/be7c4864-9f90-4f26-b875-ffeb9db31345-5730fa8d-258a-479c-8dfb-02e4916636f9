package com.examly.springapploan.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Loan loanId;
    private String loanType;
    private double interestRate;
    private double maxAmount;
    private double minAmount;
    private int minTenureMonths;
    private int maxTenureMonths;
    private String description;
    private String status;
    private double processingFee;
    private double prepaymentPenalty;
    private int gracePeriodMonths;
    private double latePaymentFee;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LoanApplication> loanApplicationList;
 
}
