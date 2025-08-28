package br.com.gcs.ms_order_service.service.impl;

import br.com.gcs.ms_order_service.domain.dto.OrderCreatedEvent;
import br.com.gcs.ms_order_service.domain.dto.OrderRequest;
import br.com.gcs.ms_order_service.domain.dto.OrderResponse;
import br.com.gcs.ms_order_service.domain.enums.OrderStatus;
import br.com.gcs.ms_order_service.domain.model.Order;
import br.com.gcs.ms_order_service.exception.ResourceNotFoundException;
import br.com.gcs.ms_order_service.messaging.OrderEventPublisher;
import br.com.gcs.ms_order_service.repository.OrderRepository;
import br.com.gcs.ms_order_service.repository.UserRepository;
import br.com.gcs.ms_order_service.security.JwtUtil;
import br.com.gcs.ms_order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final OrderEventPublisher orderEventPublisher;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            UserRepository userRepository,
            JwtUtil jwtUtil,
            OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Override
    public OrderResponse create(OrderRequest request, String token) {

        String username = jwtUtil.extractUsername(token);

        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var order = new Order();
        order.setAmount(request.amount());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(user);

        order = orderRepository.save(order);

        var orderCreatedEvent = new OrderCreatedEvent(order.getId(), user.getId(), order.getAmount(), LocalDateTime.now());
        orderEventPublisher.publishOrderCreatedEvent(orderCreatedEvent);

        return new OrderResponse(order.getId(), order.getAmount(), order.getStatus());
    }

}
