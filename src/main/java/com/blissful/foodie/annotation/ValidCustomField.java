package com.blissful.foodie.annotation;

import com.blissful.foodie.validator.CustomValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCustomField {
    String message() default "Invalid field value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
