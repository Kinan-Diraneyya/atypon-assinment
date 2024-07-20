package com.kinan.atypon.assignment.exception;

/**
 * Exception thrown when a client error (4xx) occurs while making an API request.
 * <p>
 * This exception indicates that the request made by the client was invalid or could not be processed.
 * </p>
 */
public class ClientException extends ApiException {
	
    /**
     * Constructs a new ClientException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the error
     */
    public ClientException(String message) {
        super(message);
    }
}