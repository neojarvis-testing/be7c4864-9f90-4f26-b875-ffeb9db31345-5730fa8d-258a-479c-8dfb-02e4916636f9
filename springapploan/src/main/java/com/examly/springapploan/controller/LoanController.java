package com.examly.springapploan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapploan.dto.ResponseDTO;
import com.examly.springapploan.model.Loan;
import com.examly.springapploan.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService){
        this.loanService=loanService;
    }

    //get all loans for student and Loan Manager
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans(){
        List<Loan> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans,HttpStatus.OK);

    }

    //Add Loan only for Loan Manager
    @PostMapping
    public ResponseEntity<?> addLoan(@RequestBody Loan loan){
        Loan savedLoan = loanService.addLoan(loan);
        return new ResponseEntity<>(savedLoan,HttpStatus.CREATED);
    }

    //Get loan by loanId for student and loan manager
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getAllLoan(@PathVariable long loanId){
        Loan loan = loanService.getLoan(loanId);
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    //update loan  for loan manager
    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan, @PathVariable long loanId){
        Loan updatedLoan = loanService.updateLoan(loan,loanId);
        return new ResponseEntity<>(updatedLoan,HttpStatus.OK);
    }

    //delete Loan for Loan Manager
    @DeleteMapping("/{loanId}")
    public ResponseEntity<ResponseDTO> deleteLoan(@PathVariable long loanId){
        loanService.deleteLoan(loanId);
        ResponseDTO loanReponse = new ResponseDTO();
        loanReponse.setStatus(true);
        loanReponse.setMessage("Laon successfully deleted");
        return new ResponseEntity<>(loanReponse,HttpStatus.OK);
    }


     
}
