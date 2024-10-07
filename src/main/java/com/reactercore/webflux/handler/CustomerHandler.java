package com.reactercore.webflux.handler;

import com.reactercore.webflux.dao.CustomerDao;
import com.reactercore.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest){

        Flux<Customer> customerStream = customerDao.getCustomersList();
        return ServerResponse.ok().body(customerStream, Customer.class);
    }

    public Mono<ServerResponse> loadCustomersStream(ServerRequest serverRequest){

        Flux<Customer> customerStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerStream, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest){
        int custId=Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<Customer> customerMono = customerDao.getCustomersList().filter(c -> c.getId() == custId).next();
        Flux<Customer> customerStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest){
        Mono<Customer> customerMono=serverRequest.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto-> dto.getId()+":"+ dto.getName());
        return ServerResponse.ok()
                .body(saveResponse, String.class);
    }
}

