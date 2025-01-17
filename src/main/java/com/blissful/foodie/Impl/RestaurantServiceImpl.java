package com.blissful.foodie.Impl;

import com.blissful.foodie.dto.FileData;
import com.blissful.foodie.dto.RestaurantDTO;
import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.repository.RestaurantRepository;
import com.blissful.foodie.service.FileService;
import com.blissful.foodie.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;


    @Override
    public RestaurantDTO add(RestaurantDTO restaurantDTO) {
        /*
        restaurantDTO.setId(UUID.randomUUID().toString());
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantDTO,restaurant);
        restaurant = restaurantRepository.save(restaurant);
        BeanUtils.copyProperties(restaurant,restaurantDTO);
        return restaurantDTO;*/

        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurantDTO.getName());
        if(!restaurantOptional.isPresent()) {
            restaurantRepository.save(restaurant);
        }else{
         throw new RuntimeException("restaurant name already exists in db!!");
        }
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @Override
    public RestaurantDTO get(String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid id"));
        RestaurantDTO restaurantDTO = modelMapper.map(restaurant, RestaurantDTO.class);
        return restaurantDTO;
    }


    @Override
    public Page<RestaurantDTO> getRestaurants(Pageable pageable) {
        Page<Restaurant> page = restaurantRepository.findAll(pageable);
        return page.map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class));

    }

    @Override
    public RestaurantDTO updateRestaurant(String restaurantId, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("invalid restaurantId"));
        BeanUtils.copyProperties(restaurantDTO, restaurant);
        return modelMapper.map(restaurantRepository.save(restaurant), RestaurantDTO.class);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("invalid restaurantId"));
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public List<RestaurantDTO> searchByName(String keyword) {
        return restaurantRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class)).toList();
    }

    @Override
    public Page<RestaurantDTO> getOpenRestaurants(Pageable pageable) {
        Page<Restaurant> pageRestaurant = restaurantRepository.findByOpen(true, pageable);
        return pageRestaurant.map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class));
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        //converting all Restaurant list to RestaurantDto
        return restaurantList
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .toList();
    }

    @Override
    public Page<RestaurantDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> allPages = restaurantRepository.findAll(pageable);
        return allPages.map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class));
    }

    @Override
    public FileData uploadBanner(MultipartFile banner, String restaurantId) {
        log.info("upload banner info");
        log.info("restaurantId::{}",restaurantId);
        log.info("contentType:: {},fileName:: {}",banner.getContentType(),banner.getOriginalFilename());
        String pathFile = "uploads/restaurant_banners/"+ banner.getOriginalFilename();
        FileData fileData = fileService.uploadFile(banner, pathFile);
        return fileData;
    }
}
