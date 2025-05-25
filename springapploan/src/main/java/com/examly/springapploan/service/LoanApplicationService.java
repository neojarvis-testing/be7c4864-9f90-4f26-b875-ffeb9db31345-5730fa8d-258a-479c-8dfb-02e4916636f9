package com.examly.springapploan.service;

import java.util.List;

import com.examly.springapploan.model.LoanApplication;

public interface LoanApplicationService {

    List<LoanApplication> getAllLoanApplications();

    LoanApplication getLoanApplication(long userId);

    LoanApplication updateLoanApplication(long loanApplicationId);

    void deleteLoanApplication(long loanApplicationId);

    



    
}
