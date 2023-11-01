package edu.miu.apsd.olpe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    Long id;
    String courseId;
    String courseName;
    String InstructorId;
    Boolean courseApprovalStatus;
}
