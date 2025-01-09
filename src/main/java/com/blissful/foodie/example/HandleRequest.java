package com.blissful.foodie.example;


import com.blissful.foodie.entity.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandleRequest {


    @PostMapping("/post")
    public String postData(@Valid @RequestBody User user){
        return "we got the data";
    }

}
