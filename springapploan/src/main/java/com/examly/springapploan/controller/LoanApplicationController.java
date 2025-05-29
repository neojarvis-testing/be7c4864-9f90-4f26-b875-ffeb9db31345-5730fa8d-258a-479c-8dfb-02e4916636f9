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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapploan.config.JwtTokenGen;
import com.examly.springapploan.dto.ApproveRequest;
import com.examly.springapploan.model.LoanApplication;
import com.examly.springapploan.model.LoanApplicationRequest;
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
    public ResponseEntity<?> getAllLoanApplications(@RequestHeader("Authorization") String token){
        String userRole = JwtTokenGen.getUserRole(token);
        if (userRole == null || !(userRole.equalsIgnoreCase("LoanManager"))) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
        List<LoanApplication>loanApplications = loanApplicationService.getAllLoanApplications();
        return new ResponseEntity<>(loanApplications,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addLoanApplication(
        @RequestHeader("Authorization") String token,
        @RequestBody LoanApplicationRequest loanApplication
    ){  
        String userRole = JwtTokenGen.getUserRole(token);
        if (userRole == null || userRole.equals("LoanManager") || userRole.equals("Admin")) {
            return new ResponseEntity<>("Loan Manager & Admin has no access!", HttpStatus.FORBIDDEN);
        }
        long userIdToken = JwtTokenGen.getUserIdToken(token);
        LoanApplication updLoanApplication = loanApplicationService.addLoanApplication(loanApplication, userIdToken);
        return new ResponseEntity(updLoanApplication, HttpStatus.CREATED);

    }

    //Get Loan Application specific to User access to Student
    @GetMapping(path = "/user/{userId}", produces = "application/json")
    public ResponseEntity<?> getAllLoanApplicationsOfUser(
        @RequestHeader("Authorization") String token,    
    @PathVariable long userId){
        String userRole = JwtTokenGen.getUserRole(token);
        if (userRole == null || userRole.equals("LoanManager")) {
            return new ResponseEntity<>("Loan Manager & Admin has no access!", HttpStatus.FORBIDDEN);
        }
        List<LoanApplication> result = loanApplicationService.getLoanApplication(userId);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    //Get Loan Application by Id access to Loan Manager and Student
    @GetMapping("/{loanApplicationId}")
    public ResponseEntity<LoanApplication> getLoanApplication(@PathVariable long loanApplicationId){
        LoanApplication loanApplication = loanApplicationService.getApplication(loanApplicationId);
        return new ResponseEntity<>(loanApplication,HttpStatus.OK);
    }

    //update Loan Application obj for Loan Manager
    @PutMapping("/{loanApplicationId}")
<<<<<<< HEAD
    public ResponseEntity<?> updateLoanApplication(@RequestHeader("Authorization") String token,@RequestBody LoanApplication laonApplication, @PathVariable long loanApplicationId){
        String userRole = JwtTokenGen.getUserRole(token);
        if (userRole == null || !(userRole.equalsIgnoreCase("LoanManager"))) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
=======
    public ResponseEntity<LoanApplication> updateLoanApplication(@RequestBody LoanApplication laonApplication, @PathVariable long loanApplicationId){
      
>>>>>>> 16cfda88a01848a034f8d85d422337f0ee7f99a0
        LoanApplication updatedLoanApplication = loanApplicationService.updateLoanApplication(laonApplication,loanApplicationId);
        return new ResponseEntity<>(updatedLoanApplication,HttpStatus.OK);
    }

    
    //update Loan Application : approve or reject loan
    @PutMapping("/status/{loanApplicationId}")
    public ResponseEntity<LoanApplication> approveLoanApplication(@RequestBody ApproveRequest status, @PathVariable long loanApplicationId){
       
        return new ResponseEntity<LoanApplication>(loanApplicationService
        .updateLoanApplicationStatus(status,loanApplicationId),HttpStatus.OK);
    }      

    //delete loan Application Access for student
    @DeleteMapping("/{loanApplicationId}")
    public ResponseEntity<?> deleteLoanApplication(@RequestHeader("Authorization") String token,@PathVariable long loanApplicationId){
        String userRole = JwtTokenGen.getUserRole(token);
        if (userRole == null || !(userRole.equalsIgnoreCase("LoanManager"))) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
        loanApplicationService.deleteLoanApplication(loanApplicationId);
        ResponseDTO response = new ResponseDTO();
        response.setStatus(true);
        response.setMessage("Laon successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
