package com.examly.springappfeedback.model;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



public class FeedbackRequest {

    private int feedbackId;
    private String feedbackText;
    private Date date;
    private User user;
    

    public FeedbackRequest() {
    }
    public FeedbackRequest(int feedbackId, String feedbackText, Date date, User user) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
    }
    
    public int getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getFeedbackText() {
        return feedbackText;
    }
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
