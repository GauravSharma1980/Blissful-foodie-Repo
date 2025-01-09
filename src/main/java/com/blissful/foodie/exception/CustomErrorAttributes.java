package com.blissful.foodie.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        System.out.println("from getErrorAttributes................................");

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        // Customize the error attributes
        errorAttributes.put("timestamp", "Gaurav timestamp");
        errorAttributes.put("message", "errors every where");
        errorAttributes.put("details", "description hai ye");


        // Remove unwanted attributes
        errorAttributes.remove("error");
        errorAttributes.remove("path");

        return errorAttributes;
    }
}
