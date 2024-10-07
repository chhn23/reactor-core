package com.reactercore.webflux.controller;

import com.reactercore.webflux.dto.Customer;
import com.reactercore.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/list")
    public List<Customer> getCustomers(){
       return customerService.loadAllUsers();

    }
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersStream(){
        return customerService.loadAllUsersStream();

    }
}
