package spring.cloud.kafka.order.controllers;

import spring.cloud.kafka.order.models.Orders;
import spring.cloud.kafka.order.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    //loading database with data
    @Bean
    CommandLineRunner initDB(OrderRepository orderRepository) {
        return args -> {
            log.info("Loading... " + orderRepository.save(new Orders("ABC Supplier", "microwave", 10)));
            log.info("Loading... " + orderRepository.save(new Orders("FedEx", "microprocessor", 9)));
            log.info("Loading... " + orderRepository.save(new Orders("ACME Delivery", "dishwasher", 7)));
        };
    }
}
