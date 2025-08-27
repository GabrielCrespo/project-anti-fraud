package br.com.gcs.ms_order_service.messaging;

import br.com.gcs.ms_order_service.domain.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    @Value("${app.rabbit.exchange.order}")
    private String ordersExchangeName;

    @Value("${app.rabbit.routing-keys.orderCreated}")
    private String orderCreatedRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        rabbitTemplate.convertAndSend(ordersExchangeName, orderCreatedRoutingKey, orderCreatedEvent);
    }

}
