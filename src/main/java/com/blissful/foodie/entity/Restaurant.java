package com.blissful.foodie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name="foodie_restaurant")
public class Restaurant {

    @Id
    private String id;
    private String name;
    private  String address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private boolean isOpen;
    @ManyToOne
    private User user;
    private String imagePath;
    private String imageName;
    private String banner;
    @Lob
    private String description;
    private LocalDate createdDate;



}
