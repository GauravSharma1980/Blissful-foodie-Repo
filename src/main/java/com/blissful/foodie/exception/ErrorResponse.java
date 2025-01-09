package com.blissful.foodie.exception;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;


@Data
public class ErrorResponse {
    private String message;
    private LocalTime timestamp;
}
