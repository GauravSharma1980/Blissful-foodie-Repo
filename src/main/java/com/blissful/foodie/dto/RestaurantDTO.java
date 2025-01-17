package com.blissful.foodie.dto;

import com.blissful.foodie.annotation.UniqueField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UniqueField(fieldName = "name",message = "restaurant name must be unique!!")
public class RestaurantDTO {

    private String id;
    private String name;
    private  String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "hh:mm:ss a")
    private LocalTime openTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "hh:mm:ss a")
    private LocalTime closeTime;
    private boolean isOpen;

    private String imagePath;
    private String imageName;

    //@JsonIgnore
    private String banner;
    private String description;

    @JsonProperty
    public String imageUrl(){
        return "http://localhost:8080/images/"+banner;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime createdDate;
}
