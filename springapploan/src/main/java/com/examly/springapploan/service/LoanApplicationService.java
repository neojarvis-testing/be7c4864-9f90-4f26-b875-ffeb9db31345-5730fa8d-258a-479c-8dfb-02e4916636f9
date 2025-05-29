package com.examly.springapploan.service;

import java.util.List;

import com.examly.springapploan.dto.ApproveRequest;
import com.examly.springapploan.model.LoanApplication;
import com.examly.springapploan.model.LoanApplicationRequest;

public interface LoanApplicationService {

    List<LoanApplication> getAllLoanApplications();

    List<LoanApplication> getLoanApplication(long userId);

    LoanApplication addLoanApplication(LoanApplicationRequest loanApplication, long userId);

    LoanApplication updateLoanApplication(LoanApplication loanApplication,long loanApplicationId);

    LoanApplication updateLoanApplicationStatus(ApproveRequest status, long loanApplicationId); 

    void deleteLoanApplication(long loanApplicationId);

    LoanApplication getApplication (long applicationId);




    
}
