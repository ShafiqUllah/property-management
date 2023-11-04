package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.StudentCourseDto;
import edu.miu.apsd.olpe.entity.StudentCourse;
import edu.miu.apsd.olpe.exception.StudentCourseNotFoundException;

import java.util.List;

public interface StudentCourseService {

    List<StudentCourse> getAllStudentCoursesList();
    StudentCourse addNewStudentCourse(StudentCourse newStudentCourse);
    StudentCourse getStudentCourseById(Long studentCourseId) throws StudentCourseNotFoundException;
    void deleteStudentCourseById(Long studentCourseId);
    StudentCourse updateStudentCourse(Long studentCourseId, StudentCourse editedStudentCourse);
}
