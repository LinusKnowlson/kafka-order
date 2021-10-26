package spring.cloud.kafka.order.services;

import spring.cloud.kafka.order.models.Orders;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;


public interface OrderServiceIF
{
    public abstract CollectionModel<EntityModel<Orders>> all();
    public abstract Orders createOrder(Orders createOrders);
    public abstract boolean LookUpCustomer(Object customer);
    public abstract boolean LookUpProduct(Object product);
    public abstract String createOrders(Orders createOrders, Object customer, Object contact, Object product, String productName, int Quantity);
    public abstract EntityModel<Orders> one(Long id);
    public abstract Orders replaceOrder(Orders createOrders, Long id);
    public abstract void deleteOrder(Long id);
}
