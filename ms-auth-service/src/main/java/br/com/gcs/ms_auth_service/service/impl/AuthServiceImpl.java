package br.com.gcs.ms_auth_service.service.impl;

import br.com.gcs.ms_auth_service.model.RoleEnum;
import br.com.gcs.ms_auth_service.model.User;
import br.com.gcs.ms_auth_service.model.UserRole;
import br.com.gcs.ms_auth_service.model.UserRoleId;
import br.com.gcs.ms_auth_service.model.dto.LoginRequest;
import br.com.gcs.ms_auth_service.model.dto.RegisterRequest;
import br.com.gcs.ms_auth_service.model.dto.TokenResponse;
import br.com.gcs.ms_auth_service.model.dto.UserResponse;
import br.com.gcs.ms_auth_service.repository.RoleRepository;
import br.com.gcs.ms_auth_service.repository.UserRepository;
import br.com.gcs.ms_auth_service.repository.UserRoleRepository;
import br.com.gcs.ms_auth_service.security.JwtUtil;
import br.com.gcs.ms_auth_service.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserRoleRepository userRoleRepository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new UsernameNotFoundException("Email already taken!");
        }

        var user = new User();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));

        user = userRepository.save(user);

        var role = roleRepository.findByDescription(RoleEnum.USER.name());

        UserRoleId userRoleId = new UserRoleId();
        userRoleId.setUser(user);
        userRoleId.setRole(role.get());

        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);

        userRole = userRoleRepository.save(userRole);
        user.setUsersRoles(List.of(userRole));

        return user.toResponse();
    }

    @Override
    public TokenResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new TokenResponse(jwtUtil.generateToken(userDetails.getUsername()));
    }
}
