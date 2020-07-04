package com.craftman.bankaccount;

import com.craftman.bankaccount.api.StatementApi;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.craftman.bankaccount.OperationType.DEPOSIT;
import static com.craftman.bankaccount.OperationType.WITHDRAWAL;

public class Statement implements StatementApi {
    @Setter
    @Getter
    List<Operation> operations = new ArrayList<>();

    @Override
    public void recordTransaction(final BigDecimal newAmount, final OperationType operationType) {
        Operation operation = new Operation();
        operation.setAmount(newAmount);
        operation.setDate(LocalDateTime.now());
        operation.setType(operationType);
        operation.setBalance(operationType.equals(DEPOSIT) ? this.getCurrentBalance().add(newAmount) : this.getCurrentBalance().subtract(newAmount));
        operations.add(operation);
    }

    public BigDecimal getCurrentBalance() {
        if (this.operations.isEmpty()) return BigDecimal.ZERO;

        Map<OperationType, BigDecimal> amountByOperation = this.operations.stream()
                .collect(Collectors.groupingBy(Operation::getType, Collectors.reducing(BigDecimal.ZERO, Operation::getAmount,BigDecimal::add)));

        BigDecimal totalDeposit = amountByOperation.get(DEPOSIT);
        BigDecimal totalWithDrawal = amountByOperation.get(WITHDRAWAL) != null ? amountByOperation.get(WITHDRAWAL) : BigDecimal.ZERO;

        return totalDeposit.subtract(totalWithDrawal);
    }
}
