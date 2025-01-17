package com.blissful.foodie.exception;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidFilePathException extends RuntimeException{

    public InvalidFilePathException(String message){
        super(message);
    }
}
