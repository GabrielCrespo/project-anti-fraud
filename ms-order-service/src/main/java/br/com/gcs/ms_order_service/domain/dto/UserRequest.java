package br.com.gcs.ms_order_service.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@Email(message = "Property must represente an email")
                          @NotBlank(message = "Email property must not be blank")
                          String email) {
}
