
@RestController
public class LoanApplicationController {

    //Get all Loan Applications access LoanManager
    @GetMapping("api/loanapplications")
    public ResponseEntity<List<LoanApplication>> getAllLoanApplications(){
        return null;
    }

    //Get Loan Application specific to User access to Student
    @GetMapping("api/loanapplications/users/{userId}")
    public ResponseEntity<List<LoanApplication>> getAllLoanApplicationsOfUser(@PathVariable String userId){
        return null;
    }

    //Get Loan Application by Id access to Loan Manager and Student
    @GetMapping("api/loanapplications/{loanApplicationId}")
    public ResponseEntity<LoanApplication> getLoanApplication(@PathVariable String loanApplicationId){
        return null;
    }

    //update Loan Application access for Loan Manager
    @GetMapping("api/loanapplications/{loanApplicationId}")
    public ResponseEntity<LoanApplication> updateLoanApplication(@PathVariable String loanApplicationId){
        return null;
    }

    //delete loan Application Access for student
    @GetMapping("api/loanapplications/{loanApplicationId}")
    public ResponseEntity<LoanApplication> deleteLoanApplication(@PathVariable String loanApplicationId){
        return null;
    }
    
}
