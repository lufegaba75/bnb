package com.lufegaba.bnb.controllers.error_handler;

import com.lufegaba.bnb.domain.errors.BaseErrorResponse;
import com.lufegaba.bnb.domain.errors.ErrorListResponse;
import com.lufegaba.bnb.domain.errors.ErrorResponse;
import com.lufegaba.bnb.infraestructure.exceptions.IdNotFoundException;
import com.lufegaba.bnb.infraestructure.exceptions.NoHirerException;
import com.lufegaba.bnb.infraestructure.exceptions.RoleExistsException;
import com.lufegaba.bnb.infraestructure.exceptions.UnauthorizedResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler ({IdNotFoundException.class})
    public BaseErrorResponse handleIdNotFound (RuntimeException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler ({MethodArgumentNotValidException.class})
    public BaseErrorResponse handleNotValid (MethodArgumentNotValidException exception) {
        var errors = new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return ErrorListResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler ({RoleExistsException.class})
    public BaseErrorResponse handleRoleExists (RoleExistsException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler ({UnauthorizedResourceException.class})
    public BaseErrorResponse handleUnauthorize (UnauthorizedResourceException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler ({NoHirerException.class})
    public BaseErrorResponse handleNoHirer (NoHirerException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
