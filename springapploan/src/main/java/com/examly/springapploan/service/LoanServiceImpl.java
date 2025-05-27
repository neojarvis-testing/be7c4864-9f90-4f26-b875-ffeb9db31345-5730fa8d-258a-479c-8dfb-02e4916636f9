package com.examly.springapploan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapploan.exception.LoanNotFoudException;
import com.examly.springapploan.model.Loan;
import com.examly.springapploan.repository.LoanRepository;
import com.examly.springapploan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    public LoanServiceImpl(){
    }

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository){
        this.loanRepository = loanRepository;

    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan addLoan(Loan loan) {
       return loanRepository.save(loan);
    }

    @Override
    public Loan getLoan(Long loanId) {
        return loanRepository.findById(loanId)
        .orElseThrow(()-> new LoanNotFoudException(loanId));
    }

    @Override
    public Loan updateLoan(Loan loan, long loanId) {
        Loan existingLoan = loanRepository.findById(loanId)
        .orElseThrow(()-> new LoanNotFoudException(loanId));

        existingLoan.setDescription(loan.getDescription());
        existingLoan.setGracePeriodMonths(loan.getGracePeriodMonths());
        existingLoan.setInterestRate(loan.getInterestRate());
        existingLoan.setLatePaymentFee(loan.getLatePaymentFee());
        existingLoan.setLoanType(loan.getLoanType());
        existingLoan.setMaxAmount(loan.getMaxAmount());
        existingLoan.setMaxTenureMonths(loan.getMaxTenureMonths());
        existingLoan.setMinAmount(loan.getMinAmount());
        existingLoan.setPrepaymentPenalty(loan.getPrepaymentPenalty());
    
        return loanRepository.save(existingLoan);
    
    }

    @Override
    public void deleteLoan(long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(()-> new LoanNotFoudException(loanId));
        loanRepository.delete(loan);
    }

    
}
