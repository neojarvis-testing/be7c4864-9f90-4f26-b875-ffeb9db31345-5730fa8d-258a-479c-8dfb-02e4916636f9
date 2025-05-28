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
        return loanApplicationRepository.findById(userId)
        .orElseThrow(()-> new LoanApplicationNotFound("Loan Application Not found with UserId "+userId));
    }

    @Override
    public LoanApplication updateLoanApplication(LoanApplication loanApplication, long loanApplicationId) {
        LoanApplication existingLoanApplication = loanApplicationRepository
        .findById(loanApplicationId).orElseThrow(()-> new LoanApplicationNotFound(loanApplicationId));
       existingLoanApplication.setEmploymentStatus(loanApplication.getEmploymentStatus());
       return null;
    }

    @Override
    public void deleteLoanApplication(long loanApplicationId) {
       LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
       .orElseThrow(()-> new LoanApplicationNotFound(loanApplicationId));
       loanApplicationRepository.delete(loanApplication);
    }

    @Override
    public LoanApplication addLoanApplication(LoanApplication loanApplication, Long userId) {
        loanApplication.setUserId(userId);
        return loanApplicationRepository.save(loanApplication);
    }
    
}
