package com.blissful.foodie.service;

import com.blissful.foodie.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User saveUser(User user);

    public User getUser(String userId);
}
