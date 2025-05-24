package com.examly.springapploan.contorller.LoanController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestContoller
public class LoanController {

    //get all loans for student and Loan Manager
    @GetMapping("/api/loans")
    public ResponseEntity<List<Loan>> getAllLoans(){
        return null;
    }

    //Add Loan only for Loan Manager
    @PostMapping("/api/loans")
    public ResponseEntity<?> addLoan(@RequestBody Loan loan){
        return null;
    }

    //Get loan by loanId for student and loan manager
    @GetMapping("/api/loans/{loanId}")
    public ResponseEntity<List<Loan>> getAllLoans(@PathParameter long loanId){
        return null;
    }

    //update loan  for loan manager
    @PutMapping("/api/loans/{loanId}")
    public ResponseEntity<List<Loan>> updateLoan(@PathParameter long loanId){
        return null;
    }

     
}
