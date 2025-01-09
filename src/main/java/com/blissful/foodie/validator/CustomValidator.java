package com.blissful.foodie.validator;

import com.blissful.foodie.annotation.ValidCustomField;
import com.blissful.foodie.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<ValidCustomField, String> {
    @Override
    public void initialize(ValidCustomField constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.equals("Male") || value.equals("Female") ? true:false;
    }
}
