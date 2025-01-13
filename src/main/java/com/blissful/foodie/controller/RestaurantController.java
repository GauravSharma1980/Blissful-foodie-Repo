package com.blissful.foodie.controller;


import com.blissful.foodie.dto.RestaurantDTO;
import com.blissful.foodie.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;


    @PostMapping("/add")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        restaurantDTO.setId(UUID.randomUUID().toString());
        RestaurantDTO restaurantDto = restaurantService.add(restaurantDTO);
        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Page<RestaurantDTO>> findAll(
            @RequestParam(value = "page", required = false,defaultValue = "0") int page,
            @RequestParam(value = "size", required = false,defaultValue = "10") int size,
            @RequestParam(value = "sortBy",required = false,defaultValue = "createdDate")String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "desc")String SortDir
    ){
        Sort sort = SortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return ResponseEntity.ok(restaurantService.getAllRestaurants(pageable));
    }

}
