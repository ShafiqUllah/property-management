package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.StudentCourseDto;
import edu.miu.apsd.olpe.exception.StudentCourseNotFoundException;

import java.util.List;

public interface StudentCourseService {

    List<StudentCourseDto> getAllStudentCoursesList();
    StudentCourseDto addNewStudentCourse(StudentCourseDto newStudentCourse);
    StudentCourseDto getStudentCourseById(Long studentCourseId) throws StudentCourseNotFoundException;
    void deleteStudentCourseById(Long studentCourseId);
    StudentCourseDto updateStudentCourse(Long studentCourseId,StudentCourseDto editedStudentCourse);
}
