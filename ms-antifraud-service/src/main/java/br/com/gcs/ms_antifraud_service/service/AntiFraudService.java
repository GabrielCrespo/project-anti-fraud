package br.com.gcs.ms_antifraud_service.service;

import br.com.gcs.ms_antifraud_service.domain.dto.OrderCheckedEvent;
import br.com.gcs.ms_antifraud_service.domain.dto.OrderCreatedEvent;
import br.com.gcs.ms_antifraud_service.domain.enums.Status;
import br.com.gcs.ms_antifraud_service.domain.model.Fraud;
import br.com.gcs.ms_antifraud_service.domain.model.Invoice;
import br.com.gcs.ms_antifraud_service.messaging.OrderCheckedProducer;
import br.com.gcs.ms_antifraud_service.repository.FraudRepository;
import br.com.gcs.ms_antifraud_service.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AntiFraudService {

    private final InvoiceRepository invoiceRepository;

    private final FraudRepository fraudRepository;

    private final OrderCheckedProducer orderCheckedProducer;

    public AntiFraudService(
            InvoiceRepository invoiceRepository,
            FraudRepository fraudRepository,
            OrderCheckedProducer orderCheckedProducer) {
        this.invoiceRepository = invoiceRepository;
        this.fraudRepository = fraudRepository;
        this.orderCheckedProducer = orderCheckedProducer;
    }

    public void validate(OrderCreatedEvent orderCreatedEvent, Invoice invoice) {

        invoice = invoiceRepository.save(invoice);

        if (orderCreatedEvent.amount().compareTo(new BigDecimal("1000.00")) > 0) {

            invoice.setStatus(Status.DENIED);

            var fraud = new Fraud();
            fraud.setInvoice(invoice);

            fraudRepository.save(fraud);

        } else {
            invoice.setStatus(Status.APPROVED);
        }

        invoiceRepository.save(invoice);

        var checkedOrderEvent = new OrderCheckedEvent(
                orderCreatedEvent.orderId(),
                orderCreatedEvent.userId(),
                invoice.getStatus(),
                invoice.getStatus().getReason());

        orderCheckedProducer.sendCheckedOrder(checkedOrderEvent);

    }

}
