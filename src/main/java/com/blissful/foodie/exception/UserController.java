package com.blissful.foodie.exception;

import com.blissful.foodie.dto.UserDTO;
import com.blissful.foodie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("from createUser...............");
        /*if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                                                .stream()
                                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }*/
        // Proceed with user creation logic

        UserDTO userSaved = userService.saveUser(userDTO);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUsers(@PathVariable("userId") String userId){
        UserDTO user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/list-paging")
    public ResponseEntity<Page<UserDTO>> getUsersUsingPagination(
            @RequestParam(value = "page", required = false,defaultValue = "0") int page,
            @RequestParam(value = "size", required = false,defaultValue = "10") int size,
            @RequestParam(value = "sortBy",required = false,defaultValue = "createdDate")String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "desc")String sortingType
    ){

        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));

        Page<UserDTO> allUsers = userService.getAllUsers(pageable);

        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
