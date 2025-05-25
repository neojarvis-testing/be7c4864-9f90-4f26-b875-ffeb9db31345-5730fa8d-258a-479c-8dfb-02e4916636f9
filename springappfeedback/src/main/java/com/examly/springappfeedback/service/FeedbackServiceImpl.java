package src.main.java.com.examly.springappfeedback.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappuser.repository.UserRepository;

import src.main.java.com.examly.springappfeedback.model.Feedback;
import src.main.java.com.examly.springappfeedback.model.User;
import src.main.java.com.examly.springappfeedback.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    public Feedback creatFeedback(Feedback feedback){
        if(feedback.getUser() != null){
            Optional<User> userOptional = userRepository.findById(feedback.getUser().getUserId());
            if(userOptional.isPresent()){
                feedback.setUser(userOptional.get());
                return feedbackRepository.save(feedback);
            }else{
                throw new IllegalArgumentException("User not found with ID" + feedback.getUser().getUserId());
            }
        } else{
            throw new IllegalArgumentException("User information is required to create Feedback!");
        }
    }

    public List<Feedback> getFeedbackByUserId(int userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new IllegalAccessException("user doesn't exist!");
        }
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList.stream()
        .filter(feedback -> feedback.getUser() != null && feedback.getUser().getUserId()==userId)
        .collect(Collector.toList());
    }

    public boolean deleteFeedback(int feedbackId){
        if(feedbackRepository.existsById(feedbackId)){
            feedbackRepository.deleteById(feedbackId);
            return true;
        }
        return false;
    }

}
