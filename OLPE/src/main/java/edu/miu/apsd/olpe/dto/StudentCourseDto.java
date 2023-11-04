package edu.miu.apsd.olpe.dto;

import edu.miu.apsd.olpe.entity.TeacherCourse;
import edu.miu.apsd.olpe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseDto {

    User user;
    TeacherCourse teacherCourse;
    Boolean status;
    LocalDate dateBlock;
    Boolean bookmark;
}
