package com.examly.springapploan.service;

import java.util.List;

import com.examly.springapploan.model.College;

public interface CollegeService {

    List<College> getCollegeList();

    College getCollege(int collegeId);

    College addCollege(College college);

    College updateCollege(College college, int collegeId);

    void deleteCollege(int collegeId);


    
}
