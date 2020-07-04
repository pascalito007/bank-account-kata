package com.craftman.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
        Account account = new Account();

        account.deposit(new BigDecimal(1000));
        account.withDraw(new BigDecimal(80));
        account.deposit(new BigDecimal(200));
        account.deposit(new BigDecimal(20));
        account.withDraw(new BigDecimal(900));

        account.printHistory();
    }
}
