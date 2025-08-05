package br.com.gcs.ms_auth_service.service;

import br.com.gcs.ms_auth_service.model.dto.LoginRequest;
import br.com.gcs.ms_auth_service.model.dto.RegisterRequest;
import br.com.gcs.ms_auth_service.model.dto.TokenResponse;
import br.com.gcs.ms_auth_service.model.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    TokenResponse login(LoginRequest request);

}
