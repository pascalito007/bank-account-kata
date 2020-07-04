package com.craftman.bankaccount;

import com.craftman.bankaccount.api.StatementApi;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Statement implements StatementApi {
    @Setter
    @Getter
    List<Operation> operations = new ArrayList<>();

    @Override
    public void recordTransaction(BigDecimal newAmount, OperationType operationType) {
        Operation operation = new Operation();
        operation.setAmount(newAmount);
        operation.setDate(LocalDateTime.now());
        operation.setType(operationType);
        operations.add(operation);
    }

}
