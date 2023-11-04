package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.TeacherCourseDto;
import edu.miu.apsd.olpe.entity.TeacherCourse;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;

import java.util.List;

public interface TeacherCourseService {
    List<TeacherCourse> getAllCourses();
    TeacherCourse addNewCourse(TeacherCourse newCourse);
    TeacherCourse getCourseById(Long courseId) throws CourseNotFoundException;
    void deleteCourseById(Long courseId);
    TeacherCourse updateCourse(Long courseId, TeacherCourse editedCourse);

}
