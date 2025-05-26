package src.main.java.com.examly.springappfeedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.main.java.com.examly.springappfeedback.model.Feedback;
import src.main.java.com.examly.springappfeedback.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        try {
            List<Feedback> feedbackList = feedbackService.getAllFeedback();
            return new ResponseEntity<>(feedbackList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback){
        try {
            feedbackService.creatFeedback(feedback);
            return new ResponseEntity<>("Feedback Submitted Successfull", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Provide Feedback Data properly", HttpStatus.BAD_REQUEST); 
        } catch (Exception e){
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFeedbackByUserId(@PathVariable int userId){
        try {
            List<Feedback> feedbackList = feedbackService.getFeedbackByUserId(userId);
            return new ResponseEntity<>(feedbackList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @DeleteMapping("{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable int feedbackId){
        try {
            boolean deleted = feedbackService.deleteFeedback(feedbackId);
            if(deleted){
                return new ResponseEntity<>(feedbackList, HttpStatus.OK); 
            }else{
                return new ResponseEntity<>(feedbackList, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(feedbackList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
