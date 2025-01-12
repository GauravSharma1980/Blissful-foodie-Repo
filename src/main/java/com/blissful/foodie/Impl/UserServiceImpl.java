package com.blissful.foodie.Impl;

import com.blissful.foodie.dto.UserDTO;
import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.entity.User;
import com.blissful.foodie.repository.UserRepository;
import com.blissful.foodie.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setId(UUID.randomUUID().toString());
        User updatedUser = userRepository.save(user);
        UserDTO updatedUserDto = new UserDTO();
        BeanUtils.copyProperties(user,updatedUserDto);
        return updatedUserDto;
    }

    @Override
    public UserDTO getUser(String userId) {
        UserDTO userDTO  = new UserDTO();
        User outUser =userRepository.findById(userId).get();
        System.out.println("NAME "+outUser.getName());
        BeanUtils.copyProperties(outUser,userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setId(user.getId());
            userDTO.setGender(user.getGender());
            userDTO.setEmail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setPassword(user.getPassword());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> allPage = userRepository.findAll(pageable);
        return allPage.map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setId(user.getId());
            userDTO.setGender(user.getGender());
            userDTO.setEmail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setPassword(user.getPassword());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            return userDTO;
        });

    }


}
