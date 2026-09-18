package com.hunbank.transaction;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootApplication public class TransactionServiceApplication { public static void main(String[] args) { SpringApplication.run(TransactionServiceApplication.class,args); } }
record Transaction(String id,String accountId,String type,BigDecimal amount,Instant createdAt) {}
record MoneyRequest(@NotBlank String accountId,@NotNull @DecimalMin("0.01") BigDecimal amount) {}
@RestController @RequestMapping("/transactions") class TransactionController {
  private final List<Transaction> transactions=new CopyOnWriteArrayList<>();
  @PostMapping("/deposit") @ResponseStatus(HttpStatus.CREATED) Transaction deposit(@Valid @RequestBody MoneyRequest r) { return add(r,"DEPOSIT"); }
  @PostMapping("/withdraw") @ResponseStatus(HttpStatus.CREATED) Transaction withdraw(@Valid @RequestBody MoneyRequest r) { return add(r,"WITHDRAWAL"); }
  @GetMapping("/account/{accountId}") List<Transaction> history(@PathVariable String accountId) { return transactions.stream().filter(t->t.accountId().equals(accountId)).toList(); }
  private Transaction add(MoneyRequest r,String type) { var t=new Transaction(UUID.randomUUID().toString(),r.accountId(),type,r.amount(),Instant.now()); transactions.add(t); return t; }
}
