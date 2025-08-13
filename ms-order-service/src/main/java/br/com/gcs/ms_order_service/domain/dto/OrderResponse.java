package br.com.gcs.ms_order_service.domain.dto;

import br.com.gcs.ms_order_service.domain.enums.OrderStatus;

import java.math.BigDecimal;

public record OrderResponse(Long id, BigDecimal amount, OrderStatus status) {
}
