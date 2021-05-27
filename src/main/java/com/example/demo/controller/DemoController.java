package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.jpa.Customer;
import com.example.demo.jpa.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired private CustomerRepository customers;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", customers.findById(1));
    }

    @PostMapping(path = "/members", consumes = "application/json", produces = "application/json")
    public String addMember(@RequestBody Customer customer) {
        log.info("Exists: {}", customers.existsByFirstName(customer.getFirstName()));
        customers.save(customer);
        log.info("Exists: {}", customers.existsByFirstName(customer.getFirstName()));
        return String.format("Hello %s!", customers.findByFirstName(customer.getFirstName()));
    }

}
