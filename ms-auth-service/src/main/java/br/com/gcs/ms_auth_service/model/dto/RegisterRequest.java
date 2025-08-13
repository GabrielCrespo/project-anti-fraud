package br.com.gcs.ms_auth_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@Email(message = "Property must represent an email")
                              @NotBlank(message = "Email property must not be blank") String email,
                              @NotBlank(message = "Name property must not be blank") String name,
                              @NotBlank(message = "Password property must not be blank") String password) {

}
