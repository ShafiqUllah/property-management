package edu.miu.apsd.olpe.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String courseCode;
    String courseName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User instructor;
    Boolean courseApprovalStatus;

    public Course() {
        this(null,null,null,null,null);

    }

    public Course(String courseCode, String courseName, User instructor, Boolean courseApprovalStatus) {

        this(null,courseCode,courseName,instructor,courseApprovalStatus);
    }

    public Course(Long id, String courseCode, String courseName, User instructor, Boolean courseApprovalStatus) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.courseApprovalStatus = courseApprovalStatus;
    }
}
