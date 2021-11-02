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
            log.info("Loading... " + orderRepository.save(new Orders("ABC Supplier", "microwave", 4, Long.valueOf(1),Long.valueOf(1))));
            log.info("Loading... " + orderRepository.save(new Orders("FedEx", "microprocessor", 3,Long.valueOf(1),Long.valueOf(2))));
            log.info("Loading... " + orderRepository.save(new Orders("ACME Delivery", "dishwasher", 7,Long.valueOf(2),Long.valueOf(3))));
            log.info("Loading... " + orderRepository.save(new Orders("DEF Supplier", "microwave", 5, Long.valueOf(2),Long.valueOf(1))));
            log.info("Loading... " + orderRepository.save(new Orders("SSS Delivery", "dishwasher", 2,Long.valueOf(1),Long.valueOf(3))));
        };
    }
}
