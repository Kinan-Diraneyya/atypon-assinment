package com.kinan.atypon.assignment.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global exception handler for the application.
 * <p>
 * This class handles specific exceptions and provides appropriate HTTP responses.
 * </p>
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles MissingServletRequestParameterException.
     * <p>
     * This method is invoked when a required request parameter is missing.
     * It returns a {@link ResponseEntity} with a {@link HttpStatus#BAD_REQUEST} status
     * and a message indicating the missing parameter.
     * </p>
     *
     * @param ex the exception thrown when a required parameter is missing
     * @return a ResponseEntity with a BAD_REQUEST status and a message indicating the missing parameter
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(name + " parameter is missing");
    }

    /**
     * Handles NoResourceFoundException.
     * <p>
     * This method is invoked when a requested resource is not found.
     * It returns a {@link ResponseEntity} with a {@link HttpStatus#NOT_FOUND} status
     * and the exception's message.
     * </p>
     *
     * @param ex the exception thrown when a resource is not found
     * @return a ResponseEntity with a NOT_FOUND status and the exception's message
     */
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingParams(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}