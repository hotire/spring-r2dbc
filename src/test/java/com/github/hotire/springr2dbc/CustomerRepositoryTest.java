package com.github.hotire.springr2dbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@DataR2dbcTest
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository repository;

  @PostConstruct
  void config() {
    final Flux<Customer> customerFlux = repository.saveAll(Arrays.asList(new Customer("Ho", "Tire"), new Customer("Joo", "Man")));
    StepVerifier.create(customerFlux).expectNextCount(2).verifyComplete();
  }

  @Test
  void findAll() {
    final Flux<Customer> customerFlux = repository.findAll();
    StepVerifier.create(customerFlux).expectNextCount(2).verifyComplete();
  }

  @Test
  void findByLastName() {
    final Flux<Customer> customerFlux = repository.findByLastName("Tire");
    StepVerifier.create(customerFlux)
      .consumeNextWith(customer -> assertThat(customer.getFirstName()).isEqualTo("Ho"))
      .verifyComplete();
  }

}