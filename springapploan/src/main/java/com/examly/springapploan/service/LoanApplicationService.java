package com.examly.springapploan.service;

import java.util.List;

import com.examly.springapploan.model.LoanApplication;

public interface LoanApplicationService {

    List<LoanApplication> getAllLoanApplications();

    LoanApplication getLoanApplication(long userId);

    LoanApplication addLoanApplication(LoanApplication loanApplication, Long userId);

    LoanApplication updateLoanApplication(LoanApplication loanApplication,long loanApplicationId);

    void deleteLoanApplication(long loanApplicationId);

    



    
}
