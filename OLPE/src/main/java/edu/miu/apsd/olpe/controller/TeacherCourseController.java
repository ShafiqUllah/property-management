package edu.miu.apsd.olpe.controller;


import edu.miu.apsd.olpe.entity.TeacherCourse;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.service.TeacherCourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "olpeApp/api/v1/service/private/courses" )
public class TeacherCourseController {

    TeacherCourseService teacherCourseService;

    public TeacherCourseController(TeacherCourseService teacherCourseService) {
        this.teacherCourseService = teacherCourseService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<TeacherCourse>> listOfAllCourses() {
        return ResponseEntity.ok(this.teacherCourseService.getAllCourses());

    }

    @GetMapping(value = "/list_approved")
    public ResponseEntity<List<TeacherCourse>> listOfAllApprovedCourses() {
        return ResponseEntity.ok(this.teacherCourseService.getAllCourses()
                .stream()
                .filter(TeacherCourse::getCourseApprovalStatus)
                .toList());

    }

    @GetMapping(value = "/get/{courseId}")
    public ResponseEntity<TeacherCourse> getPatientById(@PathVariable Integer courseId) throws CourseNotFoundException {
        return ResponseEntity.ok(this.teacherCourseService.getCourseById(Long.valueOf(courseId)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<TeacherCourse> registerNewCourse(@Valid @RequestBody TeacherCourse newCourse) {
        System.out.println("Reg new course");
        return new ResponseEntity<>(this.teacherCourseService.addNewCourse(newCourse), HttpStatus.CREATED);
    }

    @PutMapping(value =  "/update/{courseId}")
    public ResponseEntity<TeacherCourse> updateCourse(@PathVariable Integer courseId, @RequestBody TeacherCourse editedCourse) {
        return new ResponseEntity<>(this.teacherCourseService.updateCourse(Long.valueOf(courseId), editedCourse), HttpStatus.OK);
    }

    //Course cancel by admin/delete
    @DeleteMapping(value = "/delete/{courseId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer courseId) throws CourseNotFoundException {
        TeacherCourse teacherCourse = this.teacherCourseService.getCourseById(Long.valueOf(courseId));
        if(teacherCourse.getCourseApprovalStatus()){
            this.teacherCourseService.deleteCourseById(Long.valueOf(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping(value =  "/approve/{courseId}")
    public ResponseEntity<TeacherCourse> approveCourse(@PathVariable Integer courseId) throws CourseNotFoundException {
        TeacherCourse teacherCourse = this.teacherCourseService.getCourseById(Long.valueOf(courseId));
        teacherCourse.setCourseApprovalStatus(true);
        return new ResponseEntity<>(this.teacherCourseService.updateCourse(Long.valueOf(courseId), teacherCourse), HttpStatus.OK);
    }
}
