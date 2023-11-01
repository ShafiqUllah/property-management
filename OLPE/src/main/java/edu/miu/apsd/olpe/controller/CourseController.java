package edu.miu.apsd.olpe.controller;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/olpeApp/api/v1/courses" )
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CourseDto>> listOfAllCourses() {
        return ResponseEntity.ok(this.courseService.getAllCourses());

    }
}
