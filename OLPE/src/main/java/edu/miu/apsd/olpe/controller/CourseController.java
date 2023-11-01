package edu.miu.apsd.olpe.controller;

import edu.miu.apsd.olpe.dto.CourseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/olpeApp/api/v1/courses" )
public class CourseController {

    @GetMapping(value = "/list")
    public ResponseEntity<List<CourseDto>> listOfAllCourses() {
        //return ResponseEntity.ok(addressService.getAllAddresses());
        return ResponseEntity.ok(null);

    }
}
