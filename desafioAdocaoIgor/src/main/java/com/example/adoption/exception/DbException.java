package com.example.adoption.exception;

public class DbException extends NoStacktraceException {
   public DbException(String errorMessage) {
       super(errorMessage);
   }

   public DbException(String errorMessage, Throwable err) {
       super(errorMessage, err);
   }
}
