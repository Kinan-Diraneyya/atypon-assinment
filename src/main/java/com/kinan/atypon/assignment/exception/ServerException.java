package com.kinan.atypon.assignment.exception;

/**
 * Exception thrown when a server error (5xx) occurs while making an API request.
 * <p>
 * This exception indicates that the server encountered an error while processing the request.
 * </p>
 */
public class ServerException extends ApiException {
	
	private static final long serialVersionUID = 3568186881045362613L;

	/**
     * Constructs a new ServerException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the error
     */
    public ServerException(String message) {
        super(message);
    }
}