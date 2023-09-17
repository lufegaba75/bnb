package com.lufegaba.bnb.infraestructure.exceptions;

public class RoleExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "this role already exists for this user";

    public RoleExistsException () {
        super(String.format(ERROR_MESSAGE, ""));
    }
}
