package br.com.gcs.ms_order_service.service;

import br.com.gcs.ms_order_service.domain.dto.UserRequest;

public interface UserService {
    void create(UserRequest request);
}
