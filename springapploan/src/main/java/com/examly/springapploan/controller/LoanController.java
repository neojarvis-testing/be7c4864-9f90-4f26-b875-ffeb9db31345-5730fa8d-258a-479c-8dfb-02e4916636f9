package com.examly.springapploan.contorller.LoanController;

@RestController
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
