package br.com.gcs.ms_antifraud_service.messaging;

import br.com.gcs.ms_antifraud_service.domain.dto.OrderCreatedEvent;
import br.com.gcs.ms_antifraud_service.domain.enums.Status;
import br.com.gcs.ms_antifraud_service.domain.model.Invoice;
import br.com.gcs.ms_antifraud_service.service.AntiFraudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final AntiFraudService antiFraudService;

    public OrderConsumer(AntiFraudService antiFraudService) {
        this.antiFraudService = antiFraudService;
    }

    @RabbitListener(queues = "order.created.queue")
    public void consume(OrderCreatedEvent orderCreatedEvent) {

        var invoice = new Invoice();
        invoice.setAmount(orderCreatedEvent.amount());
        invoice.setOrderId(orderCreatedEvent.orderId());
        invoice.setUserId(orderCreatedEvent.userId());
        invoice.setOrderCreatedDateTime(orderCreatedEvent.createdAt());
        invoice.setStatus(Status.PENDING);

        LOGGER.info("📩 Recebido evento de ordem: {}", orderCreatedEvent);
        antiFraudService.validate(orderCreatedEvent, invoice);
    }

}
