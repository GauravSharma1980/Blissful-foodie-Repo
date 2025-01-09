package com.blissful.foodie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

@RestController
public class MyController {

    @GetMapping("/example")
    public String exampleMethod() {
        throw new MyCustomException("Something went wrong");
    }

    @ExceptionHandler(MyCustomException.class)
    public String handleMyCustomException(MyCustomException ex) {
        return ex.getMessage();
    }
}

class MyControllerProxy extends MyController {

    @Override
    public String exampleMethod() {
        try {
            return super.exampleMethod();
        } catch (MyCustomException ex) {
            return handleMyCustomException(ex);
        }
    }

    public String handleMyCustomException(MyCustomException ex) {
        // Logic to call the actual exception handler method using reflection
        Method method = findExceptionHandlerMethod(ex);
        try {
            return (String)method.invoke(this, ex);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Method findExceptionHandlerMethod(MyCustomException ex) {
       return null;
    }
}
