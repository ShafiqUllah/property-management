package edu.miu.apsd.olpe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {

    String courseId;
    String courseName;
    String instructorId;
    Boolean courseApprovalStatus;
}
