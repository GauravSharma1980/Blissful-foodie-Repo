package com.blissful.foodie.Impl;

import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.entity.User;
import com.blissful.foodie.repository.UserRepository;
import com.blissful.foodie.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User outUser =userRepository.findById(userId).get();
        System.out.println("NAME "+outUser.getName());
        Restaurant haldiRam = new Restaurant();
        haldiRam.setName("HaldiRam");
        haldiRam.setOpen(true);
        haldiRam.setOpenTime(LocalTime.now());
        haldiRam.setCloseTime(LocalTime.MIDNIGHT);
        haldiRam.setId(UUID.randomUUID().toString());
        outUser.getRestaurantList().add(haldiRam);
        userRepository.save(outUser);
        return userRepository.findById(userId).get();
    }
}
