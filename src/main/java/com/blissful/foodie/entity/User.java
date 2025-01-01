package com.blissful.foodie.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Table(name = "foodie_user")
@Entity
@Getter
@Setter
public class User {


    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role roles;

    private boolean isAvailable = true; // applicable to dilevery boy

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Restaurant> restaurantList = new ArrayList<>();



 }
