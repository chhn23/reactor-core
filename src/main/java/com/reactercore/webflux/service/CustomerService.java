package com.reactercore.webflux.service;

import com.reactercore.webflux.dao.CustomerDao;
import com.reactercore.webflux.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllUsers(){
        long starttime=System.currentTimeMillis();
        List<Customer> customers=customerDao.getCustomers();
        long stoptime=System.currentTimeMillis();
        log.info("Time taken:::"+(stoptime-starttime));
        return customers;

    }
    public Flux<Customer> loadAllUsersStream(){
        long starttime=System.currentTimeMillis();
        Flux<Customer> customers=customerDao.getCustomersStream();
        long stoptime=System.currentTimeMillis();
        log.info("Time taken:::"+(stoptime-starttime));
        return customers;

    }
}
