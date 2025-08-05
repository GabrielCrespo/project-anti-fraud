package br.com.gcs.ms_order_service.service.impl;

import br.com.gcs.ms_order_service.domain.dto.UserRequest;
import br.com.gcs.ms_order_service.domain.model.User;
import br.com.gcs.ms_order_service.repository.UserRepository;
import br.com.gcs.ms_order_service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserRequest request) {
        var user = new User();
        user.setEmail(request.email());
        userRepository.save(user);
    }
}
