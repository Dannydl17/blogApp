package com.dblogApp.exception;

public class InvalidDetailsException extends  RuntimeException{
    public InvalidDetailsException(){
        super("invalid email or password");
    }
}
