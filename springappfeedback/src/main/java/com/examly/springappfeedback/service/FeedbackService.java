package src.main.java.com.examly.springappfeedback.service;
import java.util.List;

import src.main.java.com.examly.springappfeedback.model.Feedback;
import src.main.java.com.examly.springappfeedback.repository.FeedbackRepository;

public interface FeedbackService {
   List<Feedback> getAllFeedback();
   Feedback creatFeedback(Feedback feedback);
   List<Feedback> getFeedbackByUserId(int userId);
   boolean deleteFeedback(int feedbackId);
}
