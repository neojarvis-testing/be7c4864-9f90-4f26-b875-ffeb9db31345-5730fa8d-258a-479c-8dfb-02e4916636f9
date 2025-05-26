package com.examly.springapploan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 
import org.springframework.web.service.annotation.DeleteExchange;

import com.examly.springapploan.dto.ResponseDTO;
import com.examly.springapploan.exception.AuthException;
import com.examly.springapploan.exception.CollegeNotFoundException;
import com.examly.springapploan.model.College;
import com.examly.springapploan.service.CollegeService; 
import com.examly.springapploan.config.JwtTokenGen;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

    private CollegeService collegeService;

    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    //Access for Admin and Stundet
    @GetMapping
    public ResponseEntity<List<College>> getAllColleges(){

      List<College> collegeList = collegeService.getCollegeList();
      return new ResponseEntity<>(collegeList,HttpStatus.OK);
    }

    //Access for Admin
    @GetMapping("/{collegeId}")
    public ResponseEntity<College> getCollege(@PathVariable int collegeId) throws CollegeNotFoundException{
        College college = collegeService.getCollege(collegeId);
        return new ResponseEntity<>(college,HttpStatus.OK);
    }

    //Access for Admin
    @PostMapping
    public ResponseEntity<College> addCollege(@RequestHeader("token")String token, @RequestBody College college) throws AuthException{
        
        String userId = JwtTokenGen.getUserId(token);
        College savedCollege = collegeService.addCollege(college);
        return new ResponseEntity<>(savedCollege,HttpStatus.CREATED);
    }

    //Access for Admin
    @PutMapping("/{collegeId}")
    public ResponseEntity<College> updateCollege(@RequestBody College college,@PathVariable int collegeId) throws CollegeNotFoundException{
        College updatedCollege = collegeService.updateCollege(college, collegeId);
        return new ResponseEntity<>(updatedCollege, HttpStatus.OK);
    }

    //Access for Admin
    @DeleteMapping("/{collegeId}")
    public ResponseEntity<ResponseDTO> deleteCollege(@PathVariable int collegeId) throws CollegeNotFoundException{
        collegeService.deleteCollege(collegeId);
       ResponseDTO response = new ResponseDTO();
       response.setStatus(true);
       response.setMessage("Laon successfully deleted");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }




    
}
