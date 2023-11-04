package edu.miu.apsd.olpe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User student_id;

    @ManyToOne
    TeacherCourse teacherCourse;

    Boolean status;
    LocalDate dateBlock;
    Boolean bookmark;

}
