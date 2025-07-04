package br.com.gcs.ms_auth_service.controller;

import br.com.gcs.ms_auth_service.model.dto.LoginRequest;
import br.com.gcs.ms_auth_service.model.dto.RegisterRequest;
import br.com.gcs.ms_auth_service.model.dto.TokenResponse;
import br.com.gcs.ms_auth_service.model.dto.UserResponse;
import br.com.gcs.ms_auth_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        var response = authService.register(request);
        URI uri = UriComponentsBuilder.fromPath("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authService.login(request));
    }
}
