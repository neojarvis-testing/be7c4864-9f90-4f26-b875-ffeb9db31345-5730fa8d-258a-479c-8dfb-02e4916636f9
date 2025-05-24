package com.examly.springapploan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapploan.model.Loan;
import com.examly.springapploan.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository LoanRepository;


    @Override
    public List<Loan> getAllLoans() {
        return null;
    }
    
}
