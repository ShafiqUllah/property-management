package edu.miu.apsd.olpe.controller;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/get/{courseId}")
    public ResponseEntity<CourseDto> getPatientById(@PathVariable Integer courseId) throws CourseNotFoundException {
        return ResponseEntity.ok(this.courseService.getCourseById(Long.valueOf(courseId)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CourseDto> registerNewCourse(@Valid @RequestBody CourseDto newCourse) {
        return new ResponseEntity<>(this.courseService.addNewCourse(newCourse), HttpStatus.CREATED);
    }

    @PutMapping(value =  "/update/{courseId}")
    public ResponseEntity<CourseDto> updatePatient(@PathVariable Integer courseId, @RequestBody CourseDto editedPatient) {
        return new ResponseEntity<>(this.courseService.updateCourse(Long.valueOf(courseId), editedPatient), HttpStatus.OK);
    }
}
