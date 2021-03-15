package com.attractor.onlineshop.exceptions;

public class ReviewNotAllowedException extends RuntimeException{
    public ReviewNotAllowedException() {
    }

    public ReviewNotAllowedException(String message) {
        super(message);
    }
}
