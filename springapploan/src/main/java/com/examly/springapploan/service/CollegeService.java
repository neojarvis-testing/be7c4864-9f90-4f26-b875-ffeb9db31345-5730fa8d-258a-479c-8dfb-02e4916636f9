package com.examly.springapploan.service;

import java.util.List;

import com.examly.springapploan.exception.CollegeNotFoundException;
import com.examly.springapploan.model.College;

public interface CollegeService {

    List<College> getCollegeList();

    College getCollege(int collegeId) throws CollegeNotFoundException ;

    College addCollege(College college);

    College updateCollege(College college, int collegeId)  throws CollegeNotFoundException;
    College updateCollegeStatus(String  status, int collegeId) throws CollegeNotFoundException;
    void deleteCollege(int collegeId) throws CollegeNotFoundException;


    
}
