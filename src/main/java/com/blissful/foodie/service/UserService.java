package com.blissful.foodie.service;

import com.blissful.foodie.dto.UserDTO;
import com.blissful.foodie.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public UserDTO saveUser(UserDTO user);

    public UserDTO getUser(String userId);

    public List<UserDTO> getAllUsers();
    public Page<UserDTO> getAllUsers(Pageable pageable);




}
