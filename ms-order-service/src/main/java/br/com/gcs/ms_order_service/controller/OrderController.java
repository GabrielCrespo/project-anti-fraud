package br.com.gcs.ms_order_service.controller;

import br.com.gcs.ms_order_service.domain.dto.OrderRequest;
import br.com.gcs.ms_order_service.domain.dto.OrderResponse;
import br.com.gcs.ms_order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request, @RequestHeader("Authorization") String token) {
        var response = orderService.create(request, token);

        URI uri = UriComponentsBuilder
                .fromPath("/id")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);

    }
}
