package com.epam.brest.course2015.app;

/**
 * Created by pavel on 28.10.15.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}