package br.com.gcs.ms_antifraud_service.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEvent(Long orderId, Long userId, BigDecimal amount, LocalDateTime createdAt) {
}
