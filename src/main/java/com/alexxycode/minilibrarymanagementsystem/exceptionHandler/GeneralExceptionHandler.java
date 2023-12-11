package com.alexxycode.minilibrarymanagementsystem.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public String notFoundExceptionHandler(NotFoundException e){
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PathNotFoundException.class)
    public String pathNotFoundExceptionHandler(){
        return ("Invalid url path");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> errorList = exception.getFieldErrors();
        for(FieldError error : errorList)
        {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return errorMap;
    }
   /* @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception){
       // Map<String, String> errorMap = new HashMap<>();
        String message =exception.toString();
        return (message.substring(message.indexOf('c',message.indexOf(':')), message.indexOf(']'+1))+ "\nemail must be unique");
    }*/

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception){
        return ("Wrong REST method called : " + exception.getMethod());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationExceptionHandler(){
        // Map<String, String> errorMap = new HashMap<>();
       return ("Please enter an ISBN usinge the pattern ABC123");
//        String message =exception.toString();
//        return (message.substring(message.indexOf('c',message.indexOf(':')), message.indexOf(']'+1))+ "\nemail must be unique");
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String, String> duplicateEntryExceptionHandler(SQLIntegrityConstraintViolationException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.forEach(e -> {
            errorMap.put(e.getMessage(),"duplicate entry/argument parameter for a unique field/parameter");
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementExceptionHandler(NoSuchElementException exception){
        return exception.getMessage();
    }

}
