package spring.cloud.kafka.order.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import spring.cloud.kafka.order.OrderApplication;
import spring.cloud.kafka.order.models.*;
import spring.cloud.kafka.order.services.OrderService;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    
    private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);
    
    private String urlCustomer = "http://localhost:8080/customers/";
	private String urlProduct = "http://localhost:8081/products/";
    
    //build rest template for getting HTTP requests
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    //find all orders
    @GetMapping("/orders")
    CollectionModel<EntityModel<Orders>> all() {
        return orderService.all();
    }

    //create new order with validation
    @PostMapping("/orders/{customerId,contactId,productId,ProductName,OrderQuantity}/orderDetails")
    ResponseEntity<String> createOrders(@Valid @RequestBody Orders createOrders, Long customerId, Long contactId, Long productId, String productName, int orderQuantity, RestTemplate restTemplate) 
    {
    	String urlCustomer = "http://localhost:8081/customers/" + customerId;
    	Object customer = restTemplate.getForObject(urlCustomer, Object.class);
    	String urlProduct = "http://localhost:8080/products/" + productId;
    	Object product = restTemplate.getForObject(urlProduct, Object.class);
    	String urlContact = "http://localhost:8081/contacts/" + contactId;
    	Object contact = restTemplate.getForObject(urlContact, Object.class);
    	String response = orderService.createOrders(createOrders, customer, contact, product, productName, orderQuantity);
    	return ResponseEntity.ok(response);
    }
    //Look up customer basic info by order
    @GetMapping("/orders/{customerId}/customers")
    ResponseEntity<String> LookUpCustomer(@Valid @RequestBody Long customerId, RestTemplate restTemplate) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8081/customers/" + customerId;
		Object customer = restTemplate.getForObject(url, Object.class);
		boolean isValid = orderService.LookUpCustomer(customer);
		if(isValid == true) 
		{
			return ResponseEntity.ok(customer.toString());
		}
		return ResponseEntity.ok("customer id not valid");
	}
    //Look up product basic info by order
    @GetMapping("/orders/{productId}/products")
    ResponseEntity<String> LookUpProduct(@Valid @RequestBody Long productId, RestTemplate restTemplate) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8080/products/" + productId;
		Object product = restTemplate.getForObject(url, Object.class);
		boolean isValid = orderService.LookUpProduct(product);
		if(isValid == true) 
		{
			return ResponseEntity.ok(product.toString());
		}
		return ResponseEntity.ok("cannot find product with id " + productId);
	}

    //find order by id
    @GetMapping("/orders/{id}")
    EntityModel<Orders> one(@PathVariable Long id) {
        return orderService.one(id);
    }

    //update order by id
    @PutMapping("/orders/{id}")
    Orders replaceOrder(@RequestBody Orders createOrders, @PathVariable Long id) {
        return orderService.replaceOrder(createOrders, id);
    }

    //delete order by id
    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
    
    //get the information from random customer and product
    @Bean
	public CommandLineRunner run(RestTemplate restTemplate, StreamBridge streamBridge) throws Exception {
		return args -> {
			//randomly choose a customer and product id
			Random rand = new Random();
			int customerNum = rand.nextInt(3) + 1;
			int productNum = rand.nextInt(3) + 1;
			urlCustomer = urlCustomer + customerNum;
			urlProduct = urlProduct + productNum;
			//request for customer and product info and log it out on console
			Object customer = restTemplate.getForObject(urlCustomer, Object.class);
			Object product = restTemplate.getForObject(urlProduct, Object.class);
			log.info(customer.toString());
			log.info(product.toString());
		};
	}
}