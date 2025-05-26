package com.examly.springappfeedback.controller;

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

import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<?> getAllFeedback(){
        try {
            List<Feedback> feedbackList = feedbackService.getAllFeedback();
            return new ResponseEntity<>(feedbackList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured while submitting request!", HttpStatus.INTERNAL_SERVER_ERROR);
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
