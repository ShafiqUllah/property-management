package edu.miu.apsd.olpe.dto;

import edu.miu.apsd.olpe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherCourseDto {
    String courseCode;
    String courseName;
    User instructor;
    Boolean courseApprovalStatus;
}
