package br.com.gcs.ms_auth_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@Email(message = "Property must represente an email") String email,
                           @NotBlank(message = "Password property must not be blank") String password) {
}
