package com.codechallenge.roles.infra.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String searchData) {
        super("Item not found for ID " + searchData);
    }


}
