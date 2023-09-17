package com.lufegaba.bnb.infraestructure.exceptions;

public class NoHirerException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Only hirers can create lodgings";

    public NoHirerException() {
        super(String.format(ERROR_MESSAGE));
    }
}
