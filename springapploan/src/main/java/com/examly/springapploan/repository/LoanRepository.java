package  com.examly.springapploan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapploan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
    
}
