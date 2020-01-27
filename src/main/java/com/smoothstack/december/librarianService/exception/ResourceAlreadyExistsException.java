package com.smoothstack.december.librarianService.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -8322521734283962416L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
