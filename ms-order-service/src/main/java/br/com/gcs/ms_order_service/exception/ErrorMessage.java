package br.com.gcs.ms_order_service.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorMessage(String message, int code, String path, Instant instant, Map<String, String> fields) {
}
