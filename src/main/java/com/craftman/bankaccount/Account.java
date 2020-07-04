package com.craftman.bankaccount;

import com.craftman.bankaccount.api.AccountApi;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.craftman.bankaccount.OperationType.DEPOSIT;
import static com.craftman.bankaccount.OperationType.WITHDRAWAL;

@Slf4j
public class Account implements AccountApi {
    private static final String AMOUNT_NOT_ALLOWED_MESSAGE = "Amount not allowed";
    private static final String INSUFFICENT_FUND_MESSAGE = "Insufficient funds to retrieve";

    @Getter
    @Setter
    BigDecimal balance = BigDecimal.ZERO;
    @Setter
    @Getter
    Statement statement = new Statement();
    BigDecimal balanceBeforOperation;

    @Override
    public void deposit(BigDecimal newAmount) {
        throwExceptionWhenIcorrectAmount(newAmount, DEPOSIT);

        this.balanceBeforOperation = this.balance;
        statement.recordTransaction(newAmount, DEPOSIT);
        this.balance = balance.add(newAmount);
    }


    @Override
    public boolean doesNewAmountAdded(BigDecimal newAmount) {
        return this.balance.equals(this.balanceBeforOperation.add(newAmount));
    }

    @Override
    public void withDraw(BigDecimal amountToWithDraw) {
        this.throwExceptionWhenIcorrectAmount(amountToWithDraw, OperationType.WITHDRAWAL);
        this.balanceBeforOperation = this.balance;
        this.statement.recordTransaction(amountToWithDraw, WITHDRAWAL);
        this.balance = this.balanceBeforOperation.equals(amountToWithDraw) ? BigDecimal.ZERO : this.balance.subtract(amountToWithDraw);
    }

    @Override
    public void printHistory() {
        log.info("OPERATION     | DATE             | AMOUNT");
        log.info("********************************************");
        log.info("");
        this.statement.getOperations()
                .forEach(operation -> log.info(new StringBuilder()
                        .append(operation.getType())
                        .append((operation.getType().equals(DEPOSIT) ? "       | " : "    | "))
                        .append(operation.getDate())
                        .append(" | ")
                        .append(operation.getAmount())
                        .append("\n")
                        .toString()));
        log.info("**********************************************");
        log.info("BALANCE: {}", this.getBalanceFromHistory());
    }

    private BigDecimal getBalanceFromHistory() {
        Map<OperationType, List<Operation>> amountByOperation = this.statement.getOperations().stream().collect(Collectors.groupingBy(Operation::getType));
        BigDecimal totalDeposit = amountByOperation.get(DEPOSIT).stream().map(Operation::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalWithDrawal = amountByOperation.get(WITHDRAWAL).stream().map(Operation::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalDeposit.subtract(totalWithDrawal);
    }

    private void throwExceptionWhenIcorrectAmount(BigDecimal newAmount, OperationType operationType) {
        if (newAmount.compareTo(BigDecimal.ZERO) <= 0 && operationType.equals(DEPOSIT)) {
            throw new IllegalArgumentException(AMOUNT_NOT_ALLOWED_MESSAGE);
        } else if (newAmount.compareTo(this.balance) > 0 && operationType.equals(WITHDRAWAL)) {
            throw new IllegalArgumentException(INSUFFICENT_FUND_MESSAGE);
        }
    }

    @Override
    public boolean doesAccountCharged(BigDecimal amountToWithDraw) {
        return this.balance.equals(this.balanceBeforOperation.subtract(amountToWithDraw));
    }

}
