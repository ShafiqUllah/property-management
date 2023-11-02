package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.StudentCourseDto;
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
        return null;
    }

    @Override
    public StudentCourseDto getStudentCourseById(Long studentCourseId) {
        return null;
    }

    @Override
    public void deleteStudentCourseById(Long studentCourseId) {

    }

    @Override
    public StudentCourseDto updateStudentCourse(Long studentCourseId, StudentCourseDto editedStudentCourse) {
        return null;
    }
}
