package com.h2sm.testtasktelda.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DuplicateKeyException.class})
    protected ResponseEntity<Object> handleDublicateRegion(final RuntimeException ex, final WebRequest request) {
        log.debug("Exception: cannot save 2 regions with the same name");
        return handleExceptionInternal(ex, "Region Name was already saved", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotfoundEntity(final RuntimeException ex, final WebRequest request) {
        log.debug("Exception: Desired region wasn't found");
        return handleExceptionInternal(ex, "Desired region wasn't found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgument(final RuntimeException ex, final WebRequest request) {
        log.debug("Exception: received argument is illegal");
        return handleExceptionInternal(ex, "Passed argument is illegal", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
