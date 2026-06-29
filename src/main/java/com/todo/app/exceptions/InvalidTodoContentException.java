package com.todo.app.exceptions;

public class InvalidTodoContentException extends RuntimeException {

    public InvalidTodoContentException(String message) {
        super(message);
    }

}
