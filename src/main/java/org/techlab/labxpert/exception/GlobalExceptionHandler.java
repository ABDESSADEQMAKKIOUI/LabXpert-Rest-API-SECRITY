package org.techlab.labxpert.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception exception){
        CustomErrorResponse exc = new CustomErrorResponse();
        exc.setStatus(HttpStatus.BAD_REQUEST.value());
        exc.setMessage(exception.getMessage());
        exc.setTimeStamp(System.currentTimeMillis());
        //System.out.println(exception);
        return new ResponseEntity<>(exc,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
        CustomErrorResponse exc = new CustomErrorResponse();
        exc.setStatus(HttpStatus.BAD_REQUEST.value());
        exc.setMessage(exception.getFieldError().getField()+" "+exception.getFieldError().getDefaultMessage());
        exc.setTimeStamp(System.currentTimeMillis());
        //System.out.println(exception);
        return new ResponseEntity<>(exc,HttpStatus.BAD_REQUEST);
    }
}
