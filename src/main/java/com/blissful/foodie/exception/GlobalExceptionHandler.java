package com.blissful.foodie.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


        /*@ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleGenericException (Exception ex)
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setTimestamp(LocalTime.now());
            errorResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map handleGenericException (MethodArgumentNotValidException methodArgumentNotValidException)
        {
            log.info("from handleGenericException...........");
            Map<String,String> errorMap = new HashMap<>();
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(objectError -> {
                errorMap.put(((FieldError)objectError).getField(),((FieldError)objectError).getDefaultMessage());
            });
            return errorMap;
        }

        @ExceptionHandler(NoSuchElementException.class )
        public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException noSuchElementException){
            log.info("from handleNoSuchElementException...........");
            Map<String,String> errorMap = new HashMap<>();
            
            //ErrorResponse errorResponse = new ErrorResponse();
            //errorResponse.setMessage("invalid value");


            return new ResponseEntity<>(ErrorResponse.builder()
                    .message("invalid input").timestamp(LocalTime.now()).build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
