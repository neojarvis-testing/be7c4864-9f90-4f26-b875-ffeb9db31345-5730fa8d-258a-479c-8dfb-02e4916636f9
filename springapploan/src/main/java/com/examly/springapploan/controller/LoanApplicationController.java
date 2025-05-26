package com.examly.springapploan.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapploan.model.LoanApplication;
import com.examly.springapploan.service.LoanApplicationService;

@RestController
@RequestMapping("api/loanapplications")
public class LoanApplicationController {

    private LoanApplicationService loanApplicationService;
 
    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService){
        this.loanApplicationService=loanApplicationService;
        
    }


    //Get all Loan Applications access LoanManager
    @GetMapping
    public ResponseEntity<List<LoanApplication>> getAllLoanApplications(){
        List<LoanApplication>loanApplications = loanApplicationService.getAllLoanApplications();
        return new ResponseEntity<>(loanApplications,HttpStatus.OK);
    }

    //Get Loan Application specific to User access to Student
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<LoanApplication>> getAllLoanApplicationsOfUser(@PathVariable long userId){
        return null;
    }

    //Get Loan Application by Id access to Loan Manager and Student
    @GetMapping("/{loanApplicationId}")
    public ResponseEntity<LoanApplication> getLoanApplication(@PathVariable long loanApplicationId){
        LoanApplication loanApplication = loanApplicationService.getLoanApplication(loanApplicationId);
        return new ResponseEntity<>(loanApplication,HttpStatus.OK);
    }

    //update Loan Application access for Loan Manager
    @PutMapping("/{loanApplicationId}")
    public ResponseEntity<LoanApplication> updateLoanApplication(@RequestBody LoanApplication laonApplication, @PathVariable long loanApplicationId){
        LoanApplication updatedLoanApplication = loanApplicationService.updateLoanApplication(laonApplication,loanApplicationId);
        return new ResponseEntity<>(updatedLoanApplication,HttpStatus.OK);
    }

    //delete loan Application Access for student
    @DeleteMapping("/{loanApplicationId}")
    public ResponseEntity<LoanApplication> deleteLoanApplication(@PathVariable long loanApplicationId){
        return null;
    }
    
}
