package br.com.gcs.ms_antifraud_service.repository;

import br.com.gcs.ms_antifraud_service.domain.model.Fraud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<Fraud, Long> {
}
