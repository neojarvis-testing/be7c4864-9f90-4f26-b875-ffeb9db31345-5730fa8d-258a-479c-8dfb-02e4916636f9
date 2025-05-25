package src.main.java.com.examly.springappfeedback.model;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;
    private User user;
    private String feedbackText;
    private Date date;
    
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
