package com.dblogApp.exception;

public class RegistrationFailedException extends Exception{
    public RegistrationFailedException(String message, Throwable cause){
        super(message, cause);
    }
}
