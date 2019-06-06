package com.epam.exception;

public class EntityAlreadyExistException extends DaoException {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
