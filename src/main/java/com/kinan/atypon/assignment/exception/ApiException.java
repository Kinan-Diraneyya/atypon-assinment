package com.kinan.atypon.assignment.exception;

/**
 * Base class for all API-related exceptions.
 * <p>
 * This exception serves as the superclass for more specific exceptions related to API errors.
 * </p>
 */
public class ApiException extends RuntimeException {
	
    /**
     * Constructs a new ApiException with the specified detail message.
     *
     * @param message the message explaining the reason for the error
     */
    public ApiException(String message) {
        super(message);
    }
}