package br.com.gcs.ms_auth_service.exception;

import java.time.Instant;

public record ErrorMessage(String message, int code, String path, Instant instant) {
}
