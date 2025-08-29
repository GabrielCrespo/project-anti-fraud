package br.com.gcs.ms_order_service.service;

import br.com.gcs.ms_order_service.domain.dto.OrderRequest;
import br.com.gcs.ms_order_service.domain.dto.OrderResponse;
import br.com.gcs.ms_order_service.domain.model.Order;

public interface OrderService {

    OrderResponse create(OrderRequest request, String token);

    Order findById(Long id);

    void updateOrderStatus(Order order);
}
