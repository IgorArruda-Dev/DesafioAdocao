package com.example.adoption.exception;

public class NotFoundException extends NoStacktraceException{

    public NotFoundException(String errorMessage){
        super(errorMessage);
    }

    public NotFoundException(String errorMessage, Throwable err){
        super(errorMessage,  err);
    }
}
