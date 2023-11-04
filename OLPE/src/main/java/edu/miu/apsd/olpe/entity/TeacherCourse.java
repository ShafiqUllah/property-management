package edu.miu.apsd.olpe.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TeacherCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String courseCode;
    String courseName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User instructor;
    Boolean courseApprovalStatus;

    public TeacherCourse() {
        this(null,null,null,null,null);

    }

    public TeacherCourse(String courseCode, String courseName, User instructor, Boolean courseApprovalStatus) {

        this(null,courseCode,courseName,instructor,courseApprovalStatus);
    }

    public TeacherCourse(Long id, String courseCode, String courseName, User instructor, Boolean courseApprovalStatus) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.courseApprovalStatus = courseApprovalStatus;
    }
}
