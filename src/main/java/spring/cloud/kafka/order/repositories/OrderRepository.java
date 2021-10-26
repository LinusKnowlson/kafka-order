package spring.cloud.kafka.order.repositories;

import spring.cloud.kafka.order.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {}

