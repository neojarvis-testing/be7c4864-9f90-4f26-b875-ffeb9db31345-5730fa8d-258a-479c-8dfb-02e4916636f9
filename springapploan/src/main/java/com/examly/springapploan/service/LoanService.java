package com.examly.springapploan.service;

import java.util.List;
import com.examly.springapploan.model.Loan;

public interface LoanService {

    List<Loan> getAllLoans();

    Loan addLoan(Loan loan);

    Loan getLoan(long loanId);

    Loan updateLoan(Loan loan, long loanId);

    void deleteLoan(long loanId);


}
