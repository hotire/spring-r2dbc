package com.github.hotire.springr2dbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

  private final CustomerRepository customerRepository;

  public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
    return ServerResponse.ok().body(customerRepository.findAll(), Customer.class);
  }
}
