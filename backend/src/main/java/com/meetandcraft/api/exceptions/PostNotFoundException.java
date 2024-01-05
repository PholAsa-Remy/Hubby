package com.meetandcraft.api.exceptions;

public class PostNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 4;

    public PostNotFoundException(String message) {
        super(message);
    }
}
