package com.craftman.bankaccount;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Operation {
    OperationType type;
    LocalDateTime date;
    BigDecimal amount;
}
