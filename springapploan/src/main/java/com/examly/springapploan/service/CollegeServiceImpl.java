package com.examly.springapploan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapploan.exception.CollegeNotFoundException;
import com.examly.springapploan.model.College;
import com.examly.springapploan.repository.CollegeRepository;

@Service
public class CollegeServiceImpl implements CollegeService {

    private CollegeRepository collegeRepository;

    public CollegeServiceImpl(){

    }

    @Autowired
    public CollegeServiceImpl(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @Override
    public List<College> getCollegeList() {
        return collegeRepository.findAll();
    }

    @Override
    public College getCollege(int collegeId) throws CollegeNotFoundException   {
       return collegeRepository.findById(collegeId).orElseThrow(()-> new CollegeNotFoundException("college Id not found"));
     
    }

    @Override
    public College addCollege(College college) {
       return collegeRepository.save(college);
    }

    @Override
    public College updateCollege(College college, int collegeId) throws CollegeNotFoundException {
        College existingCollege = collegeRepository.findById(collegeId).orElseThrow(()-> new CollegeNotFoundException(collegeId));
        existingCollege.setAddress(college.getAddress());
        existingCollege.setCollegeName(college.getCollegeName());
        existingCollege.setContactNumber(college.getContactNumber());
        existingCollege.setCourses(college.getCourses());
        existingCollege.setEmail(college.getEmail());
        existingCollege.setStatus(college.getStatus());
        existingCollege.setWebsite(college.getWebsite());
        return collegeRepository.save(existingCollege);

    }

    @Override
    public void deleteCollege(int collegeId) throws CollegeNotFoundException {
        College college = collegeRepository.findById(collegeId).orElseThrow(()-> new CollegeNotFoundException(collegeId));
        collegeRepository.delete(college);
    }
    
}
