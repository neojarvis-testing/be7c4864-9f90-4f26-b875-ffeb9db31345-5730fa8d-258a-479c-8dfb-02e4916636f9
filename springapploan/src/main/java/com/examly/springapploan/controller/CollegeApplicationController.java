package com.examly.springapploan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapploan.config.JwtTokenGen;
import com.examly.springapploan.dto.ResponseDTO;
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
    public ResponseEntity<?> getCollegeApplications(@RequestHeader("Authorization") String token){
        String userRole = JwtTokenGen.getUserRole(token);
        if (!userRole.equalsIgnoreCase("Admin")) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
       List<CollegeApplication> applications= collegeApplicationService.getCollegeApplications();
       return new ResponseEntity<>(applications,HttpStatus.OK);
    }

    //Get Application Access for Admin
    @GetMapping("/{collegeapplicationId}")
    public ResponseEntity<?> getApplication(@RequestHeader("Authorization") String token, @PathVariable int collegeapplicationId){
        String userRole = JwtTokenGen.getUserRole(token);
        if (!userRole.equalsIgnoreCase("Admin")) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
        CollegeApplication collegeApplication = collegeApplicationService.getCollegeApplication(collegeapplicationId);
        return new ResponseEntity<>(collegeApplication,HttpStatus.OK);
    }

    //Add Application Access for Student
    @PostMapping
    public ResponseEntity<?> addApplication(@RequestHeader("Authorization") String token, @RequestBody CollegeApplication collegeApplication){
        String userRole = JwtTokenGen.getUserRole(token);
        if (!userRole.equalsIgnoreCase("Student")) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
        CollegeApplication savedApplication = collegeApplicationService.addCollegeApplication(collegeApplication);
        return new ResponseEntity<>(savedApplication,HttpStatus.CREATED);
    }

    //update college application access for Admin
    @PutMapping("/{collegeapplicationId}")
    public ResponseEntity<?> updateCollegeApplication(@RequestHeader("Authorization") String token,@RequestBody CollegeApplication collegeApplication,@PathVariable int collegeapplicationId){
        String userRole = JwtTokenGen.getUserRole(token);
        if (!userRole.equalsIgnoreCase("Admin")) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
        CollegeApplication updatedCollegeApplication = collegeApplicationService.updateCollegeApplication(collegeApplication,collegeapplicationId);
        return new ResponseEntity<>(updatedCollegeApplication,HttpStatus.OK);
    }

    //delete college Application access for student
    @DeleteMapping("/{collegeapplicationId}")
    public ResponseEntity<?> deleteCollege(@RequestHeader("Authorization") String token,@PathVariable int collegeapplicationId){
        String userRole = JwtTokenGen.getUserRole(token);
        if (!userRole.equalsIgnoreCase("Student")) {
            return new ResponseEntity<>("No Access!", HttpStatus.FORBIDDEN);
        }
     collegeApplicationService.deleteCollegeApplication(collegeapplicationId);
      ResponseDTO response = new ResponseDTO();
       response.setStatus(true);
       response.setMessage("Laon successfully deleted");
      return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    
}
