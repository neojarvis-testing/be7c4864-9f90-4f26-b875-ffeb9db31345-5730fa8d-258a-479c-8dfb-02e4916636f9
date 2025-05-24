package com.examly.springapploan.service;

public interface LoanApplicationService {

    List<LoanApplication> getAllLoanApplications();

    LoanApplication getLoanApplication(long userId);

    LoanApplication updateLoanApplication(long loanApplicationId);

    String deleteLoanApplication(long loanApplicationId);

    



    
}
