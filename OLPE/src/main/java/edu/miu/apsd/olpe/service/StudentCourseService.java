package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.StudentCourseDto;

import java.util.List;

public interface StudentCourseService {

    List<StudentCourseDto> getAllStudentCoursesList();
    StudentCourseDto addNewStudentCourse(StudentCourseDto newStudentCourse);
    StudentCourseDto getStudentCourseById(Long studentCourseId);
    void deleteStudentCourseById(Long studentCourseId);
    StudentCourseDto updateStudentCourse(Long studentCourseId,StudentCourseDto editedStudentCourse);
}
