package com.blissful.foodie.dto;

import com.blissful.foodie.entity.User;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private String id;
    private String name;
    private  String address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private boolean isOpen;
    private String imagePath;
    private String imageName;
    private String banner;
    private String description;
}
