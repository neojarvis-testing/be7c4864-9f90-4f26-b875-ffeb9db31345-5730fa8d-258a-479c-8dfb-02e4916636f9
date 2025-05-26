package com.examly.springapploan.exception;

public class LoanNotFoudException extends RuntimeException {

    public LoanNotFoudException(long loanId){
        super("Loan not found with Id "+loanId);
    }

    public LoanNotFoudException(String message){
        super(message);
    }
}
