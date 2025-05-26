package com.examly.springappfeedback.service;

import java.util.List;
import com.examly.springappfeedback.model.Feedback;


public interface FeedbackService {
   List<Feedback> getAllFeedback();
   Feedback creatFeedback(Feedback feedback);
   List<Feedback> getFeedbackByUserId(long userId) throws IllegalAccessException;
   boolean deleteFeedback(int feedbackId);
}
