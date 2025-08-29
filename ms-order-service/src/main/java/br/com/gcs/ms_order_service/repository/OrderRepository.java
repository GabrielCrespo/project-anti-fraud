package br.com.gcs.ms_order_service.repository;

import br.com.gcs.ms_order_service.domain.enums.OrderStatus;
import br.com.gcs.ms_order_service.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
