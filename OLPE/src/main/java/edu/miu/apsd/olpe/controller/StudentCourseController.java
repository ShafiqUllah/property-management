package edu.miu.apsd.olpe.controller;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.StudentCourseDto;
import edu.miu.apsd.olpe.service.StudentCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/olpeApp/api/v1/student_courses" )
public class StudentCourseController {
    StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentCourseDto>> listOfAllStudentCourses() {
        return ResponseEntity.ok(this.studentCourseService.getAllStudentCoursesList());

    }
}
