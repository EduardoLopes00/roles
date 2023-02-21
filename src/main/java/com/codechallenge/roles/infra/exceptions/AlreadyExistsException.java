package com.codechallenge.roles.infra.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public<T> AlreadyExistsException(T searchData){
        super("The system couldn't complete the action because the item already exists for data " + searchData);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
