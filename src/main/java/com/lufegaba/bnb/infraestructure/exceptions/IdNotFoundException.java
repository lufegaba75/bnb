package com.lufegaba.bnb.infraestructure.exceptions;

public class IdNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Record no exists in %s";

    public IdNotFoundException (String tableName) {
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
