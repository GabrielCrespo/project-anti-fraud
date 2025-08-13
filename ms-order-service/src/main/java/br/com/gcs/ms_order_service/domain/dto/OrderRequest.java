package br.com.gcs.ms_order_service.domain.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record OrderRequest(@NotBlank(message = "Amount property must not be blank")
                           @DecimalMin(value = "1.00", message = "The minimum value allowed is 1.00")
                           @DecimalMax(value = "999999999.99", message = "The maximum value allowed is 999999999.99") BigDecimal amount) {
}
