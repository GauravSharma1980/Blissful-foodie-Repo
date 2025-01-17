package com.blissful.foodie.controller;


import com.blissful.foodie.dto.FileData;
import com.blissful.foodie.dto.RestaurantDTO;
import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.repository.RestaurantRepository;
import com.blissful.foodie.service.FileService;
import com.blissful.foodie.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FileService fileService;

    @Autowired
    private RestaurantRepository restaurantRepository;

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

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> update(
            @RequestBody RestaurantDTO restaurantDTO,
            @PathVariable String restaurantId
    ){
        return  ResponseEntity.status(HttpStatus.OK).body(restaurantService.updateRestaurant(restaurantId,restaurantDTO));
    }


    @PostMapping("/upload-banner/{restaurantId}")
    public ResponseEntity<?> uploadBanner(@RequestParam("banner")MultipartFile banner,
                                        @PathVariable String restaurantId){
        FileData fileData = restaurantService.uploadBanner(banner, restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(fileData);
    }
}
