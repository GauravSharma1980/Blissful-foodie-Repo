package com.blissful.foodie.repository;

import com.blissful.foodie.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,String> {
    List<Restaurant> findByNameContainingIgnoreCase(String keyword);
    Page<Restaurant> findByOpen(boolean flag, Pageable pageable);

    Optional<Restaurant> findByName(String value);
}
