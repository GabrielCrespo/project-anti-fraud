package br.com.gcs.ms_auth_service.service;

import br.com.gcs.ms_auth_service.model.dto.UserToSendRequest;

public interface UserService {
    void sendUser(UserToSendRequest request);
}
