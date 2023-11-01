package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    CourseDto addNewCourse(CourseDto newCourse);
    CourseDto getCourseById(Long courseId);
    void deleteCourseById(Long courseId);

}
