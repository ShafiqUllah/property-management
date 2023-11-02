package edu.miu.apsd.olpe.controller;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.StudentCourseDto;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.exception.StudentCourseNotFoundException;
import edu.miu.apsd.olpe.service.StudentCourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/get/{studentCourseId}")
    public ResponseEntity<StudentCourseDto> getStudentCourseById(@PathVariable Integer studentCourseId) throws StudentCourseNotFoundException {
        return ResponseEntity.ok(this.studentCourseService.getStudentCourseById(Long.valueOf(studentCourseId)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<StudentCourseDto> registerNewStudentCourse(@Valid @RequestBody StudentCourseDto newStudentCourse) {
        return new ResponseEntity<>(this.studentCourseService.addNewStudentCourse(newStudentCourse), HttpStatus.CREATED);
    }

    @PutMapping(value =  "/update/{studentCourseId}")
    public ResponseEntity<StudentCourseDto> updateStudentCourse(@PathVariable Integer studentCourseId, @RequestBody StudentCourseDto editedStudentCourse) {
        return new ResponseEntity<>(this.studentCourseService.updateStudentCourse(Long.valueOf(studentCourseId), editedStudentCourse), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{studentCourseId}")
    public ResponseEntity<Void> deleteStudentCourse(@PathVariable Integer studentCourseId) {
        this.studentCourseService.deleteStudentCourseById(Long.valueOf(studentCourseId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
