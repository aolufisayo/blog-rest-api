package com.phissy.blog.exception;

import com.phissy.blog.dto.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ErrorObject errObject = new ErrorObject();
        errObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errObject.setMessage(exception.getMessage());
        errObject.setTimestamp(new Date());
        return new ResponseEntity<>(errObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request){
        ErrorObject errObject = new ErrorObject();
        errObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errObject.setMessage(exception.getMessage());
        errObject.setTimestamp(new Date());
        return new ResponseEntity<>(errObject, HttpStatus.BAD_REQUEST);
    }

}
