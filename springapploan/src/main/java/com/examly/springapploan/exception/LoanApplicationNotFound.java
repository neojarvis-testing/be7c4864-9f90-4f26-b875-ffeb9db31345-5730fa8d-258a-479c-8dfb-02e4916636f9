package com.examly.springapploan.exception;

public class LoanApplicationNotFound extends RuntimeException {

    public LoanApplicationNotFound(long loanId){
        super("Loan Application not found with Id "+loanId);
    }

    public LoanApplicationNotFound(String message){
        super(message);
    }
    
}
