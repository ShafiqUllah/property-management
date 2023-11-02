package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.StudentCourseDto;
import edu.miu.apsd.olpe.entity.StudentCourse;
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
    public List<StudentCourseDto> getAllStudentCoursesList() {
        return this.studentCourseRepository.findAll().stream().map(s-> new StudentCourseDto(s.getUser(),
                s.getCourse(), s.getStatus(),s.getDateBlock(), s.getBookmark())).toList();
    }

    @Override
    public StudentCourseDto addNewStudentCourse(StudentCourseDto newStudentCourse) {
        StudentCourse studentCourse = this.studentCourseRepository.save(new StudentCourse(null,
                newStudentCourse.getUser(),
                newStudentCourse.getCourse(),
                newStudentCourse.getStatus(),
                newStudentCourse.getDateBlock(),
                newStudentCourse.getBookmark()));
        return new StudentCourseDto(studentCourse.getUser(),
                studentCourse.getCourse(),
                studentCourse.getStatus(),
                studentCourse.getDateBlock(),
                studentCourse.getBookmark());
    }

    @Override
    public StudentCourseDto getStudentCourseById(Long studentCourseId) {
        StudentCourse studentCourse = this.studentCourseRepository.findById(studentCourseId).orElseThrow();

        return new StudentCourseDto(studentCourse.getUser(),
                studentCourse.getCourse(),studentCourse.getStatus(),studentCourse.getDateBlock(),
                studentCourse.getBookmark());
    }

    @Override
    public void deleteStudentCourseById(Long studentCourseId) {
        this.studentCourseRepository.deleteById(studentCourseId);
    }

    @Override
    public StudentCourseDto updateStudentCourse(Long studentCourseId, StudentCourseDto editedStudentCourse) {
        var studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow();

        studentCourse.setUser(editedStudentCourse.getUser());
        studentCourse.setCourse(editedStudentCourse.getCourse());
        studentCourse.setStatus(editedStudentCourse.getStatus());
        studentCourse.setDateBlock(editedStudentCourse.getDateBlock());
        studentCourse.setBookmark(editedStudentCourse.getBookmark());
        studentCourseRepository.save(studentCourse);

        return new StudentCourseDto(studentCourse.getUser(),
                studentCourse.getCourse(),studentCourse.getStatus(),studentCourse.getDateBlock(),
                studentCourse.getBookmark());

    }
}
