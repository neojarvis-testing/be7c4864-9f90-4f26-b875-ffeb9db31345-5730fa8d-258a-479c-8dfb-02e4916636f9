package com.examly.springapploan.model;

import java.time.LocalDateTime;

public class CollegeApplication {
    
    private int collegeApplicationId;
    private LocalDateTime applicationDate;
    private String reasonForApplication;
    private String markSheet12th;
    private String percentage12th;
    private String school12th;
    private String status;
    // private User user;
    private College college;

    public int getCollegeApplicationId() {
        return collegeApplicationId;
    }
    public void setCollegeApplicationId(int collegeApplicationId) {
        this.collegeApplicationId = collegeApplicationId;
    }
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
    public String getReasonForApplication() {
        return reasonForApplication;
    }
    public void setReasonForApplication(String reasonForApplication) {
        this.reasonForApplication = reasonForApplication;
    }
    public String getMarkSheet12th() {
        return markSheet12th;
    }
    public void setMarkSheet12th(String markSheet12th) {
        this.markSheet12th = markSheet12th;
    }
    public String getPercentage12th() {
        return percentage12th;
    }
    public void setPercentage12th(String percentage12th) {
        this.percentage12th = percentage12th;
    }
    public String getSchool12th() {
        return school12th;
    }
    public void setSchool12th(String school12th) {
        this.school12th = school12th;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public College getCollege() {
        return college;
    }
    public void setCollege(College college) {
        this.college = college;
    }
}
