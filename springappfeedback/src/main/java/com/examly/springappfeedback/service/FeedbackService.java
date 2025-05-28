package com.examly.springappfeedback.service;

import java.util.List;

import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.model.FeedbackRequest;


public interface FeedbackService {
   List<Feedback> getAllFeedback();
   Feedback createFeedback(FeedbackRequest feedback, long userIdToken);
   List<Feedback> getFeedbackByUserId(long userId) throws IllegalAccessException;
   boolean deleteFeedback(int feedbackId);
}
