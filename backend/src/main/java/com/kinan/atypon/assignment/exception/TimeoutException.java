package com.kinan.atypon.assignment.exception;

/**
 * Exception thrown when a timeout occurs while making an API request.
 * <p>
 * This exception indicates that the request timed out while waiting for a response from the server.
 * </p>
 */
public class TimeoutException extends ApiException {
	
	private static final long serialVersionUID = 5988850411171225790L;

	/**
     * Constructs a new TimeoutException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the timeout
     */
    public TimeoutException(String message) {
        super(message);
    }
}