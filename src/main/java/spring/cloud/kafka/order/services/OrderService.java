package spring.cloud.kafka.order.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import spring.cloud.kafka.order.models.Orders;
import spring.cloud.kafka.order.models.OrdersEvent;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import spring.cloud.kafka.order.repositories.OrderRepository;

import spring.cloud.kafka.order.controllers.OrderModelAssembler;
import spring.cloud.kafka.order.controllers.OrderNotFoundException;

import org.springframework.context.ApplicationEventPublisher;

@Service
public class OrderService implements OrderServiceIF
{
    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;
    private ApplicationEventPublisher publisher; //event publisher

    public OrderService(OrderRepository repository, OrderModelAssembler assembler) {
        this.orderRepository = repository;
        this.assembler = assembler;
    }

    //find all orders
    @Override
    public CollectionModel<EntityModel<Orders>> all() {
        // TODO Auto-generated method stub
        List<EntityModel<Orders>> orders = orderRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderService.class).all()).withSelfRel());
    }
    //validate customer object
    @Override
	public boolean LookUpCustomer(Object customer) {
		// TODO Auto-generated method stub
    	if(customer != null) {
    		return true;
    	}
		return false;
	}
   //validate product object
	@Override
	public boolean LookUpProduct(Object service) {
		// TODO Auto-generated method stub
		if(service != null) {
    		return true;
    	}
		return false;
	}
	//create a new order with validation
	@Override
	public String createOrders(Orders createOrders, Object customer, Object contact, Object product, String productName, int Quantity) {
		// TODO Auto-generated method stub
		if(customer != null && product != null && contact != null)
		{
			//convert objects to strings
			String customerValue = customer.toString();
			String productValue = product.toString();
			String contactValue = contact.toString();
			int stockQuantity = 0;
			String[] fields = productValue.split(",");
			String[] customerValues = customerValue.split(",");
			String[] contactValues = contactValue.split(",");
			for(int index = 0;index < fields.length;index++)
			{
				if(fields[index].contains("stockQuantity"))
				{
					//get the stock quantity
					stockQuantity = Integer.parseInt((fields[index].split("="))[1]);
				}
			}
			if(productValue.contains(productName) == false)
			{
				return "product name is wrong";
			}
			else if(stockQuantity < Quantity)
			{
				return "no enough quantity in stock";
			}
			else 
			{
				String response = "Order Created Successfully!";
				//add customer address
				for(int index = 0;index < customerValues.length;index++)
				{
					if(customerValues[index].contains("address"))
					{
						response += customerValues[index] + "\n";
					}
				}
				//add phone number
				for(int index = 0;index < contactValues.length;index++)
				{
					if(contactValues[index].contains("phone"))
					{
						response += contactValues[index] + "\n";
					}
				}
				//add unit price
				for(int index = 0;index < fields.length;index++)
				{
					if(fields[index].contains("price"))
					{
						response += fields[index] + "\n";
					}
				}
				//after order was created, publish the event
				OrdersEvent orderEvent = new OrdersEvent(createOrders);
		        publisher.publishEvent(orderEvent);
		        //store the order
		        orderRepository.save(createOrders);
		        return response;
			}
		}
        return "Either product or customer id is wrong";
	}

    //find order information by id
    @Override
    public EntityModel<Orders> one(Long id) {
        // TODO Auto-generated method stub
        Orders orders = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return assembler.toModel(orders);
    }

    //update order
    @Override
    public Orders replaceOrder(Orders createOrders, Long id) {
        // TODO Auto-generated method stub
        return orderRepository.findById(id)
                .map(order -> {
                    order.setSupplier(createOrders.getSupplier());
                    order.setProduct(createOrders.getProduct());
                    order.setQuantity(createOrders.getQuantity());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    createOrders.setId(id);
                    return orderRepository.save(createOrders);
                });
    }

    //delete order record
    @Override
    public void deleteOrder(Long id) {
        // TODO Auto-generated method stub
        orderRepository.deleteById(id);
    }

}