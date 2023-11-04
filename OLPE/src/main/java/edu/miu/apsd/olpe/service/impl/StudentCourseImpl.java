package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.StudentCourseDto;
import edu.miu.apsd.olpe.entity.StudentCourse;
import edu.miu.apsd.olpe.exception.StudentCourseNotFoundException;
import edu.miu.apsd.olpe.repository.StudentCourseRepository;
import edu.miu.apsd.olpe.service.StudentCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseImpl implements StudentCourseService {

    StudentCourseRepository studentCourseRepository;


    public StudentCourseImpl(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public List<StudentCourse> getAllStudentCoursesList() {
        return this.studentCourseRepository.findAll();
    }

    @Override
    public StudentCourse addNewStudentCourse(StudentCourse newStudentCourse) {
       return this.studentCourseRepository.save(newStudentCourse);
//        return new StudentCourseDto(studentCourse.getStudent_id(),
//                studentCourse.getTeacherCourse(),
//                studentCourse.getStatus(),
//                studentCourse.getDateBlock(),
//                studentCourse.getBookmark());
    }

    @Override
    public StudentCourse getStudentCourseById(Long studentCourseId) throws StudentCourseNotFoundException {
        return  this.studentCourseRepository.findById(studentCourseId).orElseThrow(()->new StudentCourseNotFoundException("Data Not found"));

//        return new StudentCourseDto(studentCourse.getStudent_id(),
//                studentCourse.getTeacherCourse(),studentCourse.getStatus(),studentCourse.getDateBlock(),
//                studentCourse.getBookmark());
    }

    @Override
    public void deleteStudentCourseById(Long studentCourseId) {
        this.studentCourseRepository.deleteById(studentCourseId);
    }

    @Override
    public StudentCourse updateStudentCourse(Long studentCourseId, StudentCourse editedStudentCourse) {
        var studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow();

        studentCourse.setStudent_id(editedStudentCourse.getStudent_id());
        studentCourse.setTeacherCourse(editedStudentCourse.getTeacherCourse());
        studentCourse.setStatus(editedStudentCourse.getStatus());
        studentCourse.setDateBlock(editedStudentCourse.getDateBlock());
        studentCourse.setBookmark(editedStudentCourse.getBookmark());
        return studentCourseRepository.save(studentCourse);

//        return new StudentCourseDto(studentCourse.getStudent_id(),
//                studentCourse.getTeacherCourse(),studentCourse.getStatus(),studentCourse.getDateBlock(),
//                studentCourse.getBookmark());

    }
}
