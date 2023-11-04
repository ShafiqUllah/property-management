package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.entity.Course;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.repository.CourseRepository;
import edu.miu.apsd.olpe.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(c -> new CourseDto(c.getCourseCode(), c.getCourseName(), c.getInstructor(),c.getCourseApprovalStatus()))
                .toList();
    }

    @Override
    public CourseDto addNewCourse(CourseDto newCourse) {

        System.out.println("XXXXXXXXXXXXXXXXX"+" addNewCourse");

        Course res = courseRepository.save(new Course(
                newCourse.getCourseCode(),
                newCourse.getCourseName(),
                newCourse.getInstructor(),
                newCourse.getCourseApprovalStatus()));
        return new CourseDto(res.getCourseCode(),res.getCourseName(),res.getInstructor(), res.getCourseApprovalStatus());
    }

    @Override
    public CourseDto getCourseById(Long courseId) throws CourseNotFoundException {
        Course res =  courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("DATA not found"));

        return new CourseDto(res.getCourseCode(),res.getCourseName(),res.getInstructor(), res.getCourseApprovalStatus());
    }

    @Override
    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto editedCourse) {
        var course = courseRepository.findById(courseId).orElseThrow();

        course.setCourseCode(editedCourse.getCourseCode());
        course.setCourseName(editedCourse.getCourseName());
        course.setInstructor(editedCourse.getInstructor());
        course.setCourseApprovalStatus(editedCourse.getCourseApprovalStatus());
        courseRepository.save(course);

        return new CourseDto(course.getCourseCode(),course.getCourseName(),
                course.getInstructor(), course.getCourseApprovalStatus());
    }


}
