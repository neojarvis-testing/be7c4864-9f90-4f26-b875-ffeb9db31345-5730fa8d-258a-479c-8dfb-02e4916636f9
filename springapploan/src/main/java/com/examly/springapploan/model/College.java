package com.examly.springapploan.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class College {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Integer collegeId;
    private String collegeName;
    private String address;
    private String contactNumber;
    private String email;
    private String website;
    private String courses;
    private String status;
    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER, mappedBy="collegeApplicationId")
    private List<CollegeApplication> collegeApplicationList;
 
}
