import java.lang.annotation.Inherited;
import java.time.LocalDate;


@Entity
public class LoanApplication {
    
    @Id
    private long loanApplicationId;
    private LocalDate applicationDate;
    private double loanAmount;
    private int tenureMonths;
    private String applicationStatus;
    private String employmentStatus;
    private double annualIncome;
    private String remarks;
    private String proof;
    private String accountHolder;
    private String accountNumber;
    private String iFSCCode;
    private User user;
    private Loan loan;

    public long getLoanApplicationId() {
        return loanApplicationId;
    }
    public void setLoanApplicationId(long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    public LocalDate getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    public double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    public int getTenureMonths() {
        return tenureMonths;
    }
    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }
    public String getApplicationStatus() {
        return applicationStatus;
    }
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public String getEmploymentStatus() {
        return employmentStatus;
    }
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
    public double getAnnualIncome() {
        return annualIncome;
    }
    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getProof() {
        return proof;
    }
    public void setProof(String proof) {
        this.proof = proof;
    }
    public String getAccountHolder() {
        return accountHolder;
    }
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getiFSCCode() {
        return iFSCCode;
    }
    public void setiFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Loan getLoan() {
        return loan;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }



}
