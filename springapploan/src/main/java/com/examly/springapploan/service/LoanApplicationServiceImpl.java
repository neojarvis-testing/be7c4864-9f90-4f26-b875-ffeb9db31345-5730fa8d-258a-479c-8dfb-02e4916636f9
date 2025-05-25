package com.examly.springapploan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapploan.exception.LoanApplicationNotFound;
import com.examly.springapploan.model.LoanApplication;
import com.examly.springapploan.repository.LoanApplicationRepository;


@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private LoanApplicationRepository loanApplicationRepository;

    public LoanApplicationServiceImpl(){

    }

    @Autowired
    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public List<LoanApplication> getAllLoanApplications() {
        return loanApplicationRepository.findAll();
    }

    @Override
    public LoanApplication getLoanApplication(long userId) {
        return null;
    }

    @Override
    public LoanApplication updateLoanApplication(LoanApplication loanApplication, long loanApplicationId) {
        LoanApplication existingLoanApplication = loanApplicationRepository.findById(loanApplicationId).orElseThrow(()-> new LoanApplicationNotFound(loanApplicationId));
       existingLoanApplication.setEmploymentStatus(loanApplication.getEmploymentStatus());
       return null;
    }

    @Override
    public String deleteLoanApplication(long loanApplicationId) {
        return null;
    }

    @Override
    public LoanApplication addLoanApplication(LoanApplication loanApplication) {
        return null;
    }
    
}
