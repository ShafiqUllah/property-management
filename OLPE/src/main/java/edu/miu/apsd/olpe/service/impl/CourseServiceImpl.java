package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.entity.Course;
import edu.miu.apsd.olpe.repository.CourseRepository;
import edu.miu.apsd.olpe.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(c -> new CourseDto(c.getCourseId(), c.getCourseName(), c.getInstructorId(),c.getCourseApprovalStatus()))
                .toList();
    }

    @Override
    public CourseDto addNewCourse(CourseDto newCourse) {

        Course res = courseRepository.save(new Course(
                newCourse.getCourseId(),
                newCourse.getCourseName(),
                newCourse.getInstructorId(),
                newCourse.getCourseApprovalStatus()));
        return new CourseDto(res.getCourseId(),res.getCourseName(),res.getInstructorId(), res.getCourseApprovalStatus());
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Course res =  courseRepository.findById(courseId).orElseThrow();

        return new CourseDto(res.getCourseId(),res.getCourseName(),res.getInstructorId(), res.getCourseApprovalStatus());
    }

    @Override
    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto editedCourse) {
        var course = courseRepository.findById(courseId).orElseThrow();

        course.setCourseId(editedCourse.getCourseId());
        course.setCourseName(editedCourse.getCourseName());
        course.setInstructorId(editedCourse.getInstructorId());
        course.setCourseApprovalStatus(editedCourse.getCourseApprovalStatus());
        courseRepository.save(course);

        return new CourseDto(course.getCourseId(),course.getCourseName(),
                course.getInstructorId(), course.getCourseApprovalStatus());
    }


}
