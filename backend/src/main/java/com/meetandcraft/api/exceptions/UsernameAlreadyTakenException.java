package com.meetandcraft.api.exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{
    private static final long serialVersionUID = 3;

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
