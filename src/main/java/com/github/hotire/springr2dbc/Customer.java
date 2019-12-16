package com.github.hotire.springr2dbc;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Customer {

  @Id
  private Long id;

  private final String firstName;

  private final String lastName;

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
