package com.examly.springapploan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapploan.model.CollegeApplication;
import com.examly.springapploan.service.CollegeApplicationService;

@RestController
@RequestMapping("/api/collegeapplications")
public class CollegeApplicationController {

    private CollegeApplicationService collegeApplicationService;

    @Autowired
    public CollegeApplicationController(CollegeApplicationService collegeApplicationService) {
        this.collegeApplicationService = collegeApplicationService;
    }

    //Get all CollegeApplications Access for Admin
    @GetMapping
    public ResponseEntity<List<CollegeApplication>> getCollegeApplications(){
       List<CollegeApplication> applications= collegeApplicationService.getCollegeApplications();
       return new ResponseEntity<>(applications,HttpStatus.OK);
    }

    //Get Application Access for Admin
    @GetMapping("/{collegeapplicationId}")
    public ResponseEntity<CollegeApplication> getApplication(@PathVariable int collegeapplicationId){
        CollegeApplication collegeApplication = collegeApplicationService.getApplication(collegeapplicationId);
        return new ResponseEntity<>(collegeApplication,HttpStatus.OK);
    }

    //Add Application Access for Student
    @PostMapping
    public ResponseEntity<CollegeApplication> addApplication(@RequestBody CollegeApplication collegeApplication){
        CollegeApplication savedApplication = collegeApplicationService.addCollegeApplication(collegeApplication);
        return new ResponseEntity<>(savedApplication,HttpStatus.CREATED);
    }

    //update college application access for Admin
    public ResponseEntity<CollegeApplication> updateCollegeApplication(@RequestBody CollegeApplication collegeApplication){
        
    }
    
    
}
