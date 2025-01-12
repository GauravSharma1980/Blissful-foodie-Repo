package com.blissful.foodie.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;


@Data
@Builder
public class ErrorResponse {
    private String message;
    private LocalTime timestamp;
}
