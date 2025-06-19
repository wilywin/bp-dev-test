package com.marvel.api.exception;

import com.marvel.api.util.MarvelConstants;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException() {
        super(MarvelConstants.CHARACTER_NOT_FOUND);
    }
}
