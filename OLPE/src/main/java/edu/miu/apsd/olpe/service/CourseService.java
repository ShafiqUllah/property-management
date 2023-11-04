package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    CourseDto addNewCourse(CourseDto newCourse);
    CourseDto getCourseById(Long courseId) throws CourseNotFoundException;
    void deleteCourseById(Long courseId);
    CourseDto updateCourse(Long courseId,CourseDto editedCourse);

}
