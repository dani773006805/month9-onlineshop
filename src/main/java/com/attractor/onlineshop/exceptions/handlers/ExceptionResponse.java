package com.attractor.onlineshop.exceptions.handlers;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private LocalDateTime timeStamp;
    private String message;
    private String details;

}
