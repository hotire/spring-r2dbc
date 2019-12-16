package com.github.hotire.springr2dbc;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerRouter {

  @Bean
  public RouterFunction<ServerResponse> customerRoute(CustomerHandler handler) {
    return route(GET("/customer").and(accept(APPLICATION_JSON)), handler::findAll);
  }

  /**
   * CommandLineRunner
   * Application 구동 시점에 시점에 실행되는 코드
   */
  @Bean
  public CommandLineRunner config(CustomerRepository customerRepository) {
    return args -> customerRepository.saveAll(Arrays.asList(new Customer("Ho", "Tire"), new Customer("Joo", "Man"))).subscribe();
  }
}
