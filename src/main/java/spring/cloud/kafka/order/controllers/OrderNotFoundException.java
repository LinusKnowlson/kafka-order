package spring.cloud.kafka.order.controllers;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {
    //return error message when cannot find order id
    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}