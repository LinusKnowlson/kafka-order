package spring.cloud.kafka.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import spring.cloud.kafka.order.models.Orders;

@SpringBootApplication
public class OrderApplication {

	private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
	
	@Bean
	public Consumer<Orders> consume() {
		return input -> log.info(input.toString());
	}
}
