package com.examly.springappfeedback.controller;

import java.util.List;
import org.apache.logging.log4j.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springappfeedback.config.JwtTokenGen;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.model.FeedbackRequest;
import com.examly.springappfeedback.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private FeedbackService feedbackService; 
    private static final Logger logger = LogManager.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackController(FeedbackService feedbackService ){
        this.feedbackService = feedbackService;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllFeedback(
        @RequestHeader("Authorization") String token){
            logger.info("check Token {}", token );
        String userRole = JwtTokenGen.getUserRole(token);
            logger.info("check userRole {}", userRole );
        
        if (userRole == null || userRole.equals("Student")) {
            logger.info("check no permission " );
            return new ResponseEntity<>("Student has no access!", HttpStatus.FORBIDDEN);
        }
        try {
            List<Feedback> feedbackList = feedbackService.getAllFeedback();
            logger.info("check  permission " );
            return new ResponseEntity<>(feedbackList, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("check  error " );
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createFeedback(
        @RequestBody FeedbackRequest feedback,
        @RequestHeader("Authorization") String token){
        String userRole = JwtTokenGen.getUserRole(token);

        if (userRole == null || userRole.equals("LoanManager") || userRole.equals("Admin")) {
            return new ResponseEntity<>("Loan Manager & Admin has no access!", HttpStatus.FORBIDDEN);
        }

        try {
            long userIdToken = JwtTokenGen.getUserIdToken(token);
            feedbackService.createFeedback(feedback, userIdToken);
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
