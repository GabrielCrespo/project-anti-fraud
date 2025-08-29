package br.com.gcs.ms_antifraud_service.domain.dto;

import br.com.gcs.ms_antifraud_service.domain.enums.Status;

public record CheckedOrderEvent(Long orderId, Long userId, Status status, String reason) {
}
