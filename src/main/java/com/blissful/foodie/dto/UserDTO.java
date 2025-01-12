package com.blissful.foodie.dto;

import com.blissful.foodie.annotation.ValidCustomField;
import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class UserDTO {

    @Id
    private String id;
    @NotNull
    @Size(max = 5, message = "can't be more then 5 characters")
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

    public UserDTO() {
        name = null;
    }

    @ValidCustomField
    private String gender;
}
