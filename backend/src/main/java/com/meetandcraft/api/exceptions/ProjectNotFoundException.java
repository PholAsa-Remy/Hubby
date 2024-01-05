package com.meetandcraft.api.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
