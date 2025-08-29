package br.com.gcs.ms_order_service.domain.dto;

import br.com.gcs.ms_order_service.domain.enums.OrderStatus;

public record OrderCheckedEvent(Long orderId, OrderStatus status, String reason) {
}
