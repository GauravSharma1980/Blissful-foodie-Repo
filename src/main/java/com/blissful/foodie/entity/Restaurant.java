package com.blissful.foodie.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name="foodie_restaurant",uniqueConstraints =
        { @UniqueConstraint(columnNames = {"name"})})
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
    private LocalDateTime createdDate;



}
