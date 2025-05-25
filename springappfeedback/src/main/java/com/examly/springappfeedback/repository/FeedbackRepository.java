package src.main.java.com.examly.springappfeedback.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import src.main.java.com.examly.springappfeedback.model.Feedback;

public class FeedbackRepository extends JpaRepository<Feedback, Integer>{
    
}
