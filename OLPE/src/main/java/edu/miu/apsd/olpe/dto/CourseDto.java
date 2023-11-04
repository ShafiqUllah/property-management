package edu.miu.apsd.olpe.dto;

import edu.miu.apsd.olpe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {

    String courseId;
    String courseName;
    User instructorId;
    Boolean courseApprovalStatus;
}
