package br.com.gcs.ms_auth_service.service.impl;

import br.com.gcs.ms_auth_service.model.dto.UserToSendRequest;
import br.com.gcs.ms_auth_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendUser(UserToSendRequest request) {

        String url = "http://localhost:8081/api/user";
        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            LOGGER.info("User send with success");
            return;
        }

        if (response.getStatusCode().is4xxClientError()) {
            LOGGER.info("Erro to send User");
        }

    }
}
