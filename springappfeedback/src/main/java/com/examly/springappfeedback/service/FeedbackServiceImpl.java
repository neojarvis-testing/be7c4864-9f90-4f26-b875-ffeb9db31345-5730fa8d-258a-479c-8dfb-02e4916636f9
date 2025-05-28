package com.examly.springappfeedback.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springappfeedback.repository.UserRepository;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.model.FeedbackRequest;

import com.examly.springappfeedback.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }   

    public Feedback createFeedback(FeedbackRequest feedback, long userIdToken){
                long userId = feedback.getUser().getUserId();
                Feedback newFeedback = new Feedback();
                if(userId == userIdToken){
                    
                    newFeedback.setFeedbackId(feedback.getFeedbackId());
                    newFeedback.setFeedbackText(feedback.getFeedbackText());
                    newFeedback.setUserId(feedback.getUser().getUserId());
                    newFeedback.setDate(feedback.getDate());
                    feedbackRepository.save(newFeedback);
                }
                return newFeedback;
    }

    public List<Feedback> getFeedbackByUserId(long userId) throws IllegalAccessException{
        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<Feedback> result = new ArrayList<>();
        for(Feedback feedback : feedbacks){
            if(feedback.getUserId()==userId){
                result.add(feedback);
            }
        }
        return result;
    }

    public boolean deleteFeedback(int feedbackId){
        if(feedbackRepository.existsById(feedbackId)){
            feedbackRepository.deleteById(feedbackId);
            return true;
        }
        return false;
    }

}
