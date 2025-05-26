package com.examly.springapploan.service;

import java.util.List;

import com.examly.springapploan.model.CollegeApplication;

public interface CollegeApplicationService {

    List<CollegeApplication> getCollegeApplications();

    CollegeApplication getCollegeApplication(int id);

    CollegeApplication addCollegeApplication(CollegeApplication collegeApplication);

    CollegeApplication updateCollegeApplication(CollegeApplication collegeApplication, int collegeApplicationId);

    void deleteCollegeApplication(int collegeApplicationId);
    
}
