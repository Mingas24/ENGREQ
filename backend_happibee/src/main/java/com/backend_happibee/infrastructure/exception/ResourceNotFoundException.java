package com.backend_happibee.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String string) {
        super(string);
    }

    public ResourceNotFoundException(final Class<?> clazz, final Long userId) {
        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), userId));
    }
}
