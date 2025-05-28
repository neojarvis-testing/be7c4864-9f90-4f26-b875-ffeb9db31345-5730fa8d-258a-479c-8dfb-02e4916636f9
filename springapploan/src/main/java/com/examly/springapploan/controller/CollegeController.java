package com.examly.springapploan.controller;

import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Access for Admin and Stundet
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllColleges(@RequestHeader("Authorization") String token) {
        String userRole = JwtTokenGen.getUserRole(token);

        if (userRole == null || userRole.equals("LoanManager")) {
            return new ResponseEntity<>("Loan Manager has no access!", HttpStatus.FORBIDDEN);
        }
        List<College> collegeList = collegeService.getCollegeList();
        return new ResponseEntity<>(collegeList, HttpStatus.OK);
    }

    // Access for Admin
    @GetMapping(path = "/{collegeId}" , produces = "application/json" )
    public ResponseEntity<?> getCollege(@RequestHeader("Authorization") String token,
            @PathVariable int collegeId) throws CollegeNotFoundException {
        String userRole = JwtTokenGen.getUserRole(token);

        if (userRole == null || userRole.equals("LoanManager") || userRole.equals("Student")) {
            return new ResponseEntity<>("Student & Loan Manager has no access!", HttpStatus.FORBIDDEN);
        }
        College college = collegeService.getCollege(collegeId);
        return new ResponseEntity<>(college, HttpStatus.OK);
    }

    // Access for Admin
    @PostMapping
    public ResponseEntity<College> addCollege(
            @RequestHeader("Authorization") String token,
            @RequestBody College college) throws AuthException {
        String userRole = JwtTokenGen.getUserRole(token);

        if (userRole == null || userRole.equals("LoanManager") || userRole.equals("Student")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        College savedCollege = collegeService.addCollege(college);
        return new ResponseEntity<>(savedCollege, HttpStatus.CREATED);
    }

    // Access for Admin
    @PutMapping("/{collegeId}")
    public ResponseEntity<College> updateCollege(@RequestBody College college, @PathVariable int collegeId)
            throws CollegeNotFoundException {
        College updatedCollege = collegeService.updateCollege(college, collegeId);
        return new ResponseEntity<>(updatedCollege, HttpStatus.OK);
    }

    // Access for Admin
    @DeleteMapping("/{collegeId}")
    public ResponseEntity<ResponseDTO> deleteCollege(@PathVariable int collegeId) throws CollegeNotFoundException {
        collegeService.deleteCollege(collegeId);
        ResponseDTO response = new ResponseDTO();
        response.setStatus(true);
        response.setMessage("Laon successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
