package com.craftman.bankaccount.api;

import com.craftman.bankaccount.OperationType;

import java.math.BigDecimal;

public interface StatementApi {
    public void recordTransaction(BigDecimal newAmount, OperationType operationType);
}
