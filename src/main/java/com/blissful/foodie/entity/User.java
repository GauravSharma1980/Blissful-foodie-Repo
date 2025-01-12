package com.blissful.foodie.entity;


import com.blissful.foodie.annotation.ValidCustomField;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Table(name = "foodie_user")
@Entity
@Getter
@Setter
public class User {


    @Id
    private String id;
    @NotEmpty
    @Size(max = 50, message = "can't be more then 5 characters")
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^[a-zA-Z#]+$")
    private String password;
    private String address;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role roles;

    private boolean isAvailable = true; // applicable to dilevery boy

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Restaurant> restaurantList = new ArrayList<>();

    @ValidCustomField
    private String gender;

    @CreatedDate
    private LocalDate createdDate;




 }
