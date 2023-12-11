package com.alexxycode.minilibrarymanagementsystem.exceptionHandler;

public class PathNotFoundException extends RuntimeException{

    public PathNotFoundException(String message) {
        super(message);
    }
}
