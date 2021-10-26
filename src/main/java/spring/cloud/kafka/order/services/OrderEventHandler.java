package spring.cloud.kafka.order.services;

import spring.cloud.kafka.order.models.Orders;
import spring.cloud.kafka.order.repositories.OrderRepository;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    //listen to the event
    @EventListener
    public void handle(Orders orders) {
        orderRepository.save(orders);
    }
}
