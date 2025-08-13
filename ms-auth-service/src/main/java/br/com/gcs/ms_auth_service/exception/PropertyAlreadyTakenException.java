package br.com.gcs.ms_auth_service.exception;

public class PropertyAlreadyTakenException extends RuntimeException {
    public PropertyAlreadyTakenException(String message) {
        super(message);
    }
}
