package com.blissful.foodie.services;


import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.entity.Role;
import com.blissful.foodie.entity.User;
import com.blissful.foodie.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    User user = new User();
    List<Restaurant> restaurantList = new ArrayList<>();


    @BeforeEach
    public void init(){
        user.setName("Bharat");
        user.setEmail("gaurav@gmail.com");
        user.setAddress("Basni");
        user.setAvailable(true);
        user.setPassword("Hariom#001");
        user.setRoles(Role.ADMIN);

        Restaurant thikana = new Restaurant();
        thikana.setId(UUID.randomUUID().toString());
        thikana.setAddress("Basni");
        thikana.setOpenTime(LocalTime.now());
        thikana.setCloseTime(LocalTime.MIDNIGHT);
        thikana.setName("Thikana");

        Restaurant naivediay = new Restaurant();
        naivediay.setId(UUID.randomUUID().toString());
        naivediay.setAddress("Basni");
        naivediay.setOpenTime(LocalTime.now());
        naivediay.setCloseTime(LocalTime.MIDNIGHT);
        naivediay.setName("naivediay");

        restaurantList.add(thikana);
        restaurantList.add(naivediay);

    }

    @Test
    public void testSaveUser(){
        user.setRestaurantList(restaurantList);
        userService.saveUser(user);
    }

    @Test
    public void testGetUser(){
        User outUser = userService.getUser("1210ead4-8b6c-4a25-8576-6dec070d33f2");
        outUser.getRestaurantList().forEach(restaurant -> System.out.println(restaurant.getName()));

    }
}
