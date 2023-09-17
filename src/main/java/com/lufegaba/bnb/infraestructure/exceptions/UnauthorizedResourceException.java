package com.lufegaba.bnb.infraestructure.exceptions;

public class UnauthorizedResourceException extends RuntimeException {

    private static final String ERROR_MESSAGE = "This resource is unavailable for the user.";

    public UnauthorizedResourceException () {
        super(String.format(ERROR_MESSAGE));
    }
}
