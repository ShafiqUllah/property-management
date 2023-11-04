package edu.miu.apsd.olpe.service.impl;


import edu.miu.apsd.olpe.entity.TeacherCourse;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.repository.TeacherCourseRepository;
import edu.miu.apsd.olpe.service.TeacherCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    TeacherCourseRepository teacherCourseRepository;


    public TeacherCourseServiceImpl(TeacherCourseRepository teacherCourseRepository) {
        this.teacherCourseRepository = teacherCourseRepository;
    }

    @Override
    public List<TeacherCourse> getAllCourses() {
        return teacherCourseRepository.findAll().stream()
                .map(c -> new TeacherCourse(c.getCourseCode(), c.getCourseName(), c.getInstructor(),c.getCourseApprovalStatus()))
                .toList();
    }

    @Override
    public TeacherCourse addNewCourse(TeacherCourse newCourse) {

        System.out.println("XXXXXXXXXXXXXXXXX"+" addNewCourse");

        TeacherCourse res = teacherCourseRepository.save(new TeacherCourse(
                newCourse.getCourseCode(),
                newCourse.getCourseName(),
                newCourse.getInstructor(),
                newCourse.getCourseApprovalStatus()));
        return new TeacherCourse(res.getCourseCode(),res.getCourseName(),res.getInstructor(), res.getCourseApprovalStatus());
    }

    @Override
    public TeacherCourse getCourseById(Long courseId) throws CourseNotFoundException {
        TeacherCourse res =  teacherCourseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("DATA not found"));

        return new TeacherCourse(res.getCourseCode(),res.getCourseName(),res.getInstructor(), res.getCourseApprovalStatus());
    }

    @Override
    public void deleteCourseById(Long courseId) {
        teacherCourseRepository.deleteById(courseId);
    }

    @Override
    public TeacherCourse updateCourse(Long courseId, TeacherCourse editedCourse) {
        var course = teacherCourseRepository.findById(courseId).orElseThrow();

        course.setCourseCode(editedCourse.getCourseCode());
        course.setCourseName(editedCourse.getCourseName());
        course.setInstructor(editedCourse.getInstructor());
        course.setCourseApprovalStatus(editedCourse.getCourseApprovalStatus());
        teacherCourseRepository.save(course);

        return new TeacherCourse(course.getCourseCode(),course.getCourseName(),
                course.getInstructor(), course.getCourseApprovalStatus());
    }


}
