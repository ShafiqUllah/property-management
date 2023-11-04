package edu.miu.apsd.olpe.dto;

import edu.miu.apsd.olpe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    String courseCode;
    String courseName;
    User instructor;
    Boolean courseApprovalStatus;
}
