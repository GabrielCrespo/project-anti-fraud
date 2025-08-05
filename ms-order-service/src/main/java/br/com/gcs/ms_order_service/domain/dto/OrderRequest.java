package br.com.gcs.ms_order_service.domain.dto;

import java.math.BigDecimal;

public record OrderRequest(BigDecimal amount) {
}
