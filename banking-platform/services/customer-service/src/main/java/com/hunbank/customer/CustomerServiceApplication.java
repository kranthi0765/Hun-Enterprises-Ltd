package com.hunbank.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class CustomerServiceApplication {
  public static void main(String[] args) { SpringApplication.run(CustomerServiceApplication.class, args); }
}

record Customer(String id, String name, String email) {}
record CustomerRequest(@NotBlank String name, @Email @NotBlank String email) {}
@RestController
@RequestMapping("/customers")
class CustomerController {
  private final Map<String, Customer> customers = new ConcurrentHashMap<>();
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Customer create(@Valid @RequestBody CustomerRequest request) {
    var customer = new Customer(UUID.randomUUID().toString(), request.name(), request.email());
    customers.put(customer.id(), customer); return customer;
  }
  @GetMapping("/{id}")
  Customer get(@PathVariable String id) { var c = customers.get(id); if (c == null) throw new NoSuchElementException("Customer not found"); return c; }
  @GetMapping List<Customer> all() { return customers.values().stream().toList(); }
}
