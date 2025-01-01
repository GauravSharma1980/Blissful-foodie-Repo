package com.blissful.foodie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

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

    private boolean isOpen = true;

    @ManyToOne
    private User user;

}
