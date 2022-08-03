package com.example.musinsa.exception;

import net.bytebuddy.implementation.bytecode.Throw;

import javax.sql.rowset.spi.TransactionalWriter;

public class NotEnoughPointException extends RuntimeException {
    public NotEnoughPointException() {
        super();
    }

    public NotEnoughPointException(String message) {
        super(message);
    }

    public NotEnoughPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPointException(Throwable cause) {
        super(cause);
    }
}
