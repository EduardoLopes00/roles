package com.codechallenge.roles.infra.exceptions;

public class NotFoundException extends RuntimeException{
    public<T> NotFoundException(T searchData) {
        super("Item not found for ID " + searchData);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
