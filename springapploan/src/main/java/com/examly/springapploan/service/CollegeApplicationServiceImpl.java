package com.examly.springapploan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapploan.exception.CollegeApplicationNotFoundException;
import com.examly.springapploan.model.CollegeApplication;
import com.examly.springapploan.repository.CollegeApplicationRepository;

@Service
public class CollegeApplicationServiceImpl implements CollegeApplicationService {

    private CollegeApplicationRepository collegeApplicationRepository;
 
    @Autowired
    public CollegeApplicationServiceImpl(CollegeApplicationRepository collegeApplicationRepository){
        this.collegeApplicationRepository = collegeApplicationRepository;
    }

    @Override
    public List<CollegeApplication> getCollegeApplications() {
       return collegeApplicationRepository.findAll();
    }

    @Override
    public CollegeApplication getCollegeApplication(int id) {
        return collegeApplicationRepository.findById(id).orElseThrow(() -> new CollegeApplicationNotFoundException(id));
    }

    @Override
    public CollegeApplication addCollegeApplication(CollegeApplication collegeApplication) {
        return collegeApplicationRepository.save(collegeApplication);
    }

    @Override
    public CollegeApplication updateCollegeApplication(CollegeApplication collegeApplication,
            int id) {
        CollegeApplication existingCollegeApplication = collegeApplicationRepository
        .findById(id).orElseThrow(() -> new CollegeApplicationNotFoundException(id));
        existingCollegeApplication.setApplicationDate(collegeApplication.getApplicationDate());
        existingCollegeApplication.setCollege(collegeApplication.getCollege());
        existingCollegeApplication.setMarkSheet12th(collegeApplication.getMarkSheet12th());
        existingCollegeApplication.setPercentage12th(collegeApplication.getPercentage12th());
        existingCollegeApplication.setSchool12th(collegeApplication.getSchool12th());
        existingCollegeApplication.setReasonForApplication(collegeApplication.getReasonForApplication());
        return collegeApplicationRepository.save(existingCollegeApplication);

    }

    @Override
    public void deleteCollegeApplication(int collegeApplicationId) {
        CollegeApplication existingCollegeApplication = collegeApplicationRepository.findById(collegeApplicationId)
        .orElseThrow(() -> new CollegeApplicationNotFoundException(collegeApplicationId));
        collegeApplicationRepository.delete(existingCollegeApplication);
    }


}
