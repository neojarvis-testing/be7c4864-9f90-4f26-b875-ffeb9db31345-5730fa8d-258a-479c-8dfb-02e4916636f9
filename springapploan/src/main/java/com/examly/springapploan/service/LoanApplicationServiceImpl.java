package com.examly.springapploan.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapploan.dto.ApproveRequest;
import com.examly.springapploan.exception.LoanApplicationNotFound; 
import com.examly.springapploan.model.LoanApplication;
import com.examly.springapploan.model.LoanApplicationRequest;
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
    public List<LoanApplication> getLoanApplication(long userId) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAll();
        List<LoanApplication> result = new ArrayList<>();
        for(LoanApplication loanApplication : loanApplications){
            if(loanApplication.getUserId() == userId){
                result.add(loanApplication);
            }
            
        }
        return result;
    }

    @Override
    public LoanApplication updateLoanApplication(LoanApplication loanApplication, long loanApplicationId) {
        LoanApplication existingLoanApplication = loanApplicationRepository
        .findById(loanApplicationId).orElseThrow(()-> new LoanApplicationNotFound(loanApplicationId));
       existingLoanApplication.setApplicationStatus(loanApplication.getApplicationStatus());
       return existingLoanApplication;
    }

    public LoanApplication getApplication (long applicationId){
        return loanApplicationRepository
        .findById(applicationId).orElseThrow(()-> new LoanApplicationNotFound(applicationId));
    }

    @Override
    public void deleteLoanApplication(long loanApplicationId) {
       LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
       .orElseThrow(()-> new LoanApplicationNotFound(loanApplicationId));
       loanApplicationRepository.delete(loanApplication);
    }

    @Override
    public LoanApplication addLoanApplication(LoanApplicationRequest loanApplicationReq, long userId) {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setUserId(userId);
        loanApplication.setLoanId(loanApplicationReq.getLoan().getLoanId());
        loanApplication.setLoanAmount(loanApplicationReq.getLoanAmount());
        loanApplication.setApplicationDate(loanApplicationReq.getApplicationDate());
        loanApplication.setApplicationStatus(loanApplicationReq.getStatus());
        return loanApplicationRepository.save(loanApplication);
    }
    
}
