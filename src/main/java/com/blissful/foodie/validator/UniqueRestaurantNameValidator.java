package com.blissful.foodie.validator;

import com.blissful.foodie.annotation.UniqueField;
import com.blissful.foodie.entity.Restaurant;
import com.blissful.foodie.repository.RestaurantRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueRestaurantNameValidator implements ConstraintValidator<UniqueField,String> {
    private String fieldName;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Object propertyValue = new BeanWrapperImpl(value).getPropertyValue(fieldName);
        Optional<Restaurant> restaurant = restaurantRepository.findByName(value);
        if(restaurant.isPresent()){
            return false;
        }else{
            return true;
        }

    }
}
