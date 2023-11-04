package edu.miu.apsd.olpe.advice;

import edu.miu.apsd.olpe.exception.CourseNotFoundException;
import edu.miu.apsd.olpe.exception.StudentCourseNotFoundException;
import edu.miu.apsd.olpe.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class OlpeAppWebApiExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleUserAuthBadCredentialException(BadCredentialsException bcEx) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", bcEx.getMessage());
        errorMap.put("errorDisplayText", "Invalid Username and/or Password!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleDataValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errorMap = new HashMap<String, String>();
        methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errorMap.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );
        return errorMap;
    }

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handlePatientNotFoundException(CourseNotFoundException courseNotFoundException) {
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("errorMessage", courseNotFoundException.getMessage());
        return errorMessageMap;
    }

    @ExceptionHandler(StudentCourseNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handlePatientNotFoundException(StudentCourseNotFoundException courseNotFoundException) {
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("errorMessage", courseNotFoundException.getMessage());
        return errorMessageMap;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handlePatientNotFoundException(UserNotFoundException courseNotFoundException) {
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("errorMessage", courseNotFoundException.getMessage());
        return errorMessageMap;
    }
}
