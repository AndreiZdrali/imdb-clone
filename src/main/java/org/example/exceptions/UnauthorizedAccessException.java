package org.example.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException() {
        super("Unauthorized access: you do not have the required permissions to access this resource");
    }
}
