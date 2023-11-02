package edu.miu.apsd.olpe.dto;

import edu.miu.apsd.olpe.entity.Course;
import edu.miu.apsd.olpe.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseDto {

    User user;
    Course course;
    Boolean status;
    LocalDate dateBlock;
    Boolean bookmark;
}
