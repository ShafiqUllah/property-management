package edu.miu.apsd.olpe.controller;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.UserDto;
import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.exception.UserNotFoundException;
import edu.miu.apsd.olpe.service.CourseService;
import edu.miu.apsd.olpe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "olpeApp/api/v1/service/private/users" )
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Collection<UserDto>> listOfAllUsers() {
        System.out.println("XXX list");
        return ResponseEntity.ok(this.userService.getAll());

    }

    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<UserDto> getUserDetailsById(@PathVariable Integer userId) throws UserNotFoundException {
        System.out.println("Get called");
        return ResponseEntity.ok(this.userService.getById(Long.valueOf(userId)));
    }

    @PostMapping(value = "/register/{password}")
    public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto newUser,@PathVariable String password) {
        System.out.println("reg called");
        return new ResponseEntity<>(this.userService.add(newUser,password), HttpStatus.CREATED);
    }

    @PutMapping(value =  "/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto editedUser) {
        return new ResponseEntity<>(this.userService.update(Long.valueOf(userId), editedUser), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userId) {
        System.out.println("Delete called");
        this.userService.delete(Long.valueOf(userId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
