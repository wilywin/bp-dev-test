package com.marvel.api.exception;

import com.marvel.api.util.MarvelConstants;

public class DuplicateCharacterNameException extends RuntimeException {
    public DuplicateCharacterNameException(String message) {
        super(message);
    }
    public DuplicateCharacterNameException() {
        super(MarvelConstants.DUPLICATE_CHARACTER_NAME);
    }
}
