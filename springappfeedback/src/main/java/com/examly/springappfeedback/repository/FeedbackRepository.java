package com.examly.springappfeedback.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.examly.springappfeedback.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
}
