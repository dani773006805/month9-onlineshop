package com.attractor.onlineshop.exceptions;

public class QuantityErrorException extends RuntimeException{
    public QuantityErrorException() {
    }

    public QuantityErrorException(String message) {
        super(message);
    }
}
