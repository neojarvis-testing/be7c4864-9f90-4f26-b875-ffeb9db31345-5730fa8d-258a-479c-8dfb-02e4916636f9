package com.examly.springappfeedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springappfeedback.config.JwtTokenGen;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private FeedbackService feedbackService; 

    @Autowired
    private FeedbackController(FeedbackService feedbackService ){
        this.feedbackService = feedbackService;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllFeedback(
        @RequestHeader("Authorization") String token){
        String userRole = JwtTokenGen.getUserRole(token);
        if (userRole == null || userRole.equals("Student")) {
            return new ResponseEntity<>("Student has no access!", HttpStatus.FORBIDDEN);
        }
        try {
            List<Feedback> feedbackList = feedbackService.getAllFeedback();
            return new ResponseEntity<>(feedbackList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createFeedback(
        @RequestBody Feedback feedback,
        @RequestHeader("Authorization") String token){
        String userRole = JwtTokenGen.getUserRole(token);

        if (userRole == null || userRole.equals("LoanManager") || userRole.equals("Admin")) {
            return new ResponseEntity<>("Loan Manager & Admin has no access!", HttpStatus.FORBIDDEN);
        }

        try {
            feedbackService.creatFeedback(feedback);
            return new ResponseEntity<>("Feedback Submitted Successfull", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Provide Feedback Data properly", HttpStatus.BAD_REQUEST); 
        } catch (Exception e){
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @GetMapping(path = "/user/{userId}",  produces = "application/json")
    public ResponseEntity<?> getFeedbackByUserId(@PathVariable int userId,
    @RequestHeader("Authorization") String token){
        String userRole = JwtTokenGen.getUserRole(token);

        if (userRole == null || userRole.equals("LoanManager") || userRole.equals("Admin")) {
            return new ResponseEntity<>("Loan Manager & Admin has no access!", HttpStatus.FORBIDDEN);
        }
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
            boolean feedbackList = feedbackService.deleteFeedback(feedbackId);
            if(feedbackList){
                return new ResponseEntity<>("feedback deleted", HttpStatus.OK); 
            }else{
                return new ResponseEntity<>("feedback id didn't exists", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
