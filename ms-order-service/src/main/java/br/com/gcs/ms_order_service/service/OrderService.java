package br.com.gcs.ms_order_service.service;

import br.com.gcs.ms_order_service.domain.dto.OrderRequest;
import br.com.gcs.ms_order_service.domain.dto.OrderResponse;

public interface OrderService {
    OrderResponse create(OrderRequest request, String token);
}
