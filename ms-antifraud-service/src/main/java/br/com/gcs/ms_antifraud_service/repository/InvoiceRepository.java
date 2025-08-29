package br.com.gcs.ms_antifraud_service.repository;

import br.com.gcs.ms_antifraud_service.domain.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
