package spring.cloud.kafka.order.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import spring.cloud.kafka.order.models.Orders;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Orders, EntityModel<Orders>> {
    //order model assembler
    @Override
    public EntityModel<Orders> toModel(Orders orders) {

        return EntityModel.of(orders,
                linkTo(methodOn(OrderController.class).one(orders.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));
    }
}