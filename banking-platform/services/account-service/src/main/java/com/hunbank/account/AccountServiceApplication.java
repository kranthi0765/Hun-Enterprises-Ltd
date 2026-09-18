package com.hunbank.account;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication public class AccountServiceApplication { public static void main(String[] args) { SpringApplication.run(AccountServiceApplication.class,args); } }
record Account(String id, String customerId, String currency, BigDecimal balance) {}
record AccountRequest(@NotBlank String customerId, @NotBlank String currency) {}
@RestController @RequestMapping("/accounts") class AccountController {
  private final Map<String,Account> accounts = new ConcurrentHashMap<>();
  @PostMapping @ResponseStatus(HttpStatus.CREATED) Account create(@Valid @RequestBody AccountRequest r) { var a=new Account(UUID.randomUUID().toString(),r.customerId(),r.currency().toUpperCase(Locale.ROOT),BigDecimal.ZERO); accounts.put(a.id(),a); return a; }
  @GetMapping("/{id}") Account get(@PathVariable String id) { var a=accounts.get(id); if(a==null) throw new NoSuchElementException("Account not found"); return a; }
  @GetMapping List<Account> all() { return accounts.values().stream().toList(); }
}
