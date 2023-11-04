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
@RequestMapping(value = "olpeApp/api/v1/service/private/student_courses" )
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

    //Cancel by Admin
    @DeleteMapping(value = "/delete/{studentCourseId}")
    public ResponseEntity<Void> deleteStudentCourse(@PathVariable Integer studentCourseId) {
        this.studentCourseService.deleteStudentCourseById(Long.valueOf(studentCourseId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Approve a course for a student
    @PutMapping(value =  "/approve/{studentCourseId}")
    public ResponseEntity<StudentCourseDto> ApproveStudentCourse(@PathVariable Integer studentCourseId) throws StudentCourseNotFoundException {
        StudentCourseDto editedStudentCourse =  this.studentCourseService.getStudentCourseById(Long.valueOf(studentCourseId));
        editedStudentCourse.setStatus(true);
        return new ResponseEntity<>(this.studentCourseService.updateStudentCourse(Long.valueOf(studentCourseId), editedStudentCourse), HttpStatus.OK);
    }


    //Get all studentCourse list with studentId
    @GetMapping(value = "/get_all/{studentId}")
    public ResponseEntity<List<StudentCourseDto>> getStudentCourseByStudentOrUserId(@PathVariable Integer studentId) throws StudentCourseNotFoundException {
        List<StudentCourseDto> studentCourseDtoList = this.studentCourseService.getAllStudentCoursesList()
                .stream()
                .filter(a->{
                    if (a.getUser().getUserId() == studentId)
                        return true;
                    else
                        return false;

                }).toList();
        return ResponseEntity.ok(studentCourseDtoList);
    }

    //Get only approved studentCourse list with studentId
    @GetMapping(value = "/get_all/approved/{studentId}")
    public ResponseEntity<List<StudentCourseDto>> getStudentCourseByStudentOrUserIdOnlyApproved(@PathVariable Integer studentId) throws StudentCourseNotFoundException {
        List<StudentCourseDto> studentCourseDtoList = this.studentCourseService.getAllStudentCoursesList()
                .stream()
                .filter(a->{
                    if ((a.getUser().getUserId() == studentId) && a.getStatus() == true)
                        return true;
                    else
                        return false;
                })
                .toList();
        return ResponseEntity.ok(studentCourseDtoList);
    }

    //Cancel StudentCourse by Student if in pending status
    @DeleteMapping(value = "/delete/pending/{studentCourseId}")
    public ResponseEntity<Void> deleteStudentCourseByStudent(@PathVariable Integer studentCourseId) throws StudentCourseNotFoundException {
        StudentCourseDto studentCourseDto = this.studentCourseService.getStudentCourseById(Long.valueOf(studentCourseId));
        if(studentCourseDto.getStatus() == false){
            this.studentCourseService.deleteStudentCourseById(Long.valueOf(studentCourseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //Bookmark course by student
    @PutMapping(value =  "/update/bookmark/{studentCourseId}")
    public ResponseEntity<StudentCourseDto> bookmarkStudentCourseByStudent(@PathVariable Integer studentCourseId) throws StudentCourseNotFoundException {
        StudentCourseDto editedStudentCourse = this.studentCourseService.getStudentCourseById(Long.valueOf(studentCourseId));
        editedStudentCourse.setBookmark(true);
        return new ResponseEntity<>(this.studentCourseService.updateStudentCourse(Long.valueOf(studentCourseId), editedStudentCourse), HttpStatus.OK);
    }
}
