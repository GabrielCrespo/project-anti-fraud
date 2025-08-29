package br.com.gcs.ms_antifraud_service.messaging;

import br.com.gcs.ms_antifraud_service.domain.dto.CheckedOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderCheckedProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCheckedProducer.class);

    @Value("${app.rabbit.exchange.order}")
    private String ordersExchangeName;

    @Value("${app.rabbit.routing-keys.fraudChecked}")
    private String fraudCheckedRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public OrderCheckedProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCheckedOrder(CheckedOrderEvent checkedOrderEvent) {
        rabbitTemplate.convertAndSend(ordersExchangeName, fraudCheckedRoutingKey, checkedOrderEvent);
        LOGGER.info("✅ Evento de validação enviado: {}", checkedOrderEvent);
    }
}
