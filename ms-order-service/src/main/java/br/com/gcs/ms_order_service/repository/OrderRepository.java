package br.com.gcs.ms_order_service.repository;

import br.com.gcs.ms_order_service.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
