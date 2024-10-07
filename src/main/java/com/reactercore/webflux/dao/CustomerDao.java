package com.reactercore.webflux.dao;

import com.reactercore.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {
    public List<Customer> getCustomers()  {
     return IntStream.rangeClosed(1,10)
             .peek(i->System.out.println("processing count "+i))
             .peek(i-> {
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             })
             .mapToObj(i-> new Customer(i,"customer"+i)).collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i->System.out.println("processing count "+i))
                .map(i->new Customer(i,"customer"+i));
    }

    public Flux<Customer> getCustomersList()  {
        return Flux.range(1,10)
                //.delayElements(Duration.ofSeconds(1))
                .doOnNext(i->System.out.println("processing count "+i))
                .map(i->new Customer(i,"customer"+i));
    }
}
