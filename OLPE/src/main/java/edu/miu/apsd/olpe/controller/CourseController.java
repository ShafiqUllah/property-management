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

    @GetMapping(value = "/list_approved")
    public ResponseEntity<List<CourseDto>> listOfAllApprovedCourses() {
        return ResponseEntity.ok(this.courseService.getAllCourses()
                .stream()
                .filter(CourseDto::getCourseApprovalStatus)
                .toList());

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
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Integer courseId, @RequestBody CourseDto editedCourse) {
        return new ResponseEntity<>(this.courseService.updateCourse(Long.valueOf(courseId), editedCourse), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{courseId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer courseId) {
        CourseDto courseDto = this.courseService.getCourseById(Long.valueOf(courseId));
        if(courseDto.getCourseApprovalStatus()){
            this.courseService.deleteCourseById(Long.valueOf(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping(value =  "/approve/{courseId}")
    public ResponseEntity<CourseDto> approveCourse(@PathVariable Integer courseId) {
        CourseDto courseDto = this.courseService.getCourseById(Long.valueOf(courseId));
        courseDto.setCourseApprovalStatus(true);
        return new ResponseEntity<>(this.courseService.updateCourse(Long.valueOf(courseId),courseDto), HttpStatus.OK);
    }
}
