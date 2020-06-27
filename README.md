# Spring-R2DBC

![reactive](/doc/img/reactive.jpg)

## Getting Started 

- https://blog.naver.com/gngh0101/221739538578

- reference : https://spring.io/guides/gs/accessing-data-r2dbc/

### Entity

```java
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
```


### Repository

```java
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
  @Query("SELECT * FROM customer WHERE last_name = :lastname")
  Flux<Customer> findByLastName(String lastName);
}
```

### Test

```java
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
```

