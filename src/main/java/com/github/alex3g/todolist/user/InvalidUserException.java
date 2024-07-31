package com.github.alex3g.todolist.user;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String message) {
        super(message);
    }
}
