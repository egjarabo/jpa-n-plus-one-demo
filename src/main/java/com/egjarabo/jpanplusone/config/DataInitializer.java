package com.egjarabo.jpanplusone.config;

import com.egjarabo.jpanplusone.customer.Customer;
import com.egjarabo.jpanplusone.customer.CustomerRepository;
import com.egjarabo.jpanplusone.order.PurchaseOrder;
import com.egjarabo.jpanplusone.order.PurchaseOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    CommandLineRunner loadData(
            CustomerRepository customerRepository,
            PurchaseOrderRepository purchaseOrderRepository
    ) {
        return args -> {
            Customer alice = customerRepository.save(new Customer("Alice"));
            Customer bob = customerRepository.save(new Customer("Bob"));
            Customer carol = customerRepository.save(new Customer("Carol"));

            purchaseOrderRepository.save(new PurchaseOrder("Laptop replacement", alice));
            purchaseOrderRepository.save(new PurchaseOrder("Keyboard order", bob));
            purchaseOrderRepository.save(new PurchaseOrder("Monitor request", carol));
            purchaseOrderRepository.save(new PurchaseOrder("Dock station purchase", alice));
            purchaseOrderRepository.save(new PurchaseOrder("Mouse request", bob));
        };
    }
}
