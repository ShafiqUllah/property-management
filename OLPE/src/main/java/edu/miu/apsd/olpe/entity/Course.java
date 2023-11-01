package edu.miu.apsd.olpe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String courseId;
    String courseName;
    String instructorId;
    Boolean courseApprovalStatus;

    public Course() {
        this(null, null,null,null,null);
    }

    public Course(String courseId, String courseName, String instructorId, Boolean courseApprovalStatus) {
        this(null, courseId,courseName,instructorId,courseApprovalStatus);
    }

    public Course(Long id, String courseId, String courseName, String instructorId, Boolean courseApprovalStatus) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.courseApprovalStatus = courseApprovalStatus;
    }
}
