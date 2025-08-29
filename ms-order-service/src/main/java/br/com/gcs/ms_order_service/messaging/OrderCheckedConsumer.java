package br.com.gcs.ms_order_service.messaging;

import br.com.gcs.ms_order_service.domain.dto.OrderCheckedEvent;
import br.com.gcs.ms_order_service.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCheckedConsumer {

    private final OrderService orderService;

    public OrderCheckedConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "order.created.queue")
    public void consumeCheckedOrder(OrderCheckedEvent orderCheckedEvent) {
        var order = orderService.findById(orderCheckedEvent.orderId());
        order.setStatus(orderCheckedEvent.status());
        orderService.updateOrderStatus(order);
    }

}
