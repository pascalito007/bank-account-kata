package com.craftman.bankaccount;

import com.craftman.bankaccount.api.AccountApi;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static com.craftman.bankaccount.OperationType.DEPOSIT;
import static com.craftman.bankaccount.OperationType.WITHDRAWAL;

@Slf4j
public class Account implements AccountApi {
    private static final String AMOUNT_NOT_ALLOWED_MESSAGE = "Amount not allowed";
    private static final String INSUFFICENT_FUND_MESSAGE = "Insufficient funds to retrieve";

    @Getter
    @Setter
    Statement statement = new Statement();
    BigDecimal balanceBeforOperation;

    @Override
    public void deposit(BigDecimal newAmount) {
        throwExceptionWhenInValidAmount(newAmount, DEPOSIT);

        this.balanceBeforOperation = this.statement.getCurrentBalance();
        statement.recordTransaction(newAmount, DEPOSIT);
    }


    @Override
    public boolean doesNewAmountAdded(BigDecimal newAmount) {

        return this.statement.getCurrentBalance().equals(this.balanceBeforOperation.add(newAmount));
    }

    @Override
    public void withDraw(BigDecimal amountToWithDraw) {
        this.throwExceptionWhenInValidAmount(amountToWithDraw, OperationType.WITHDRAWAL);

        this.balanceBeforOperation = this.statement.getCurrentBalance();
        this.statement.recordTransaction(amountToWithDraw, WITHDRAWAL);
    }

    @Override
    public void printHistory() {
        log.info("OPERATION     | DATE             | AMOUNT    | BALANCE");
        log.info("********************************************************");
        log.info("");
        this.statement.getOperations()
                .forEach(operation -> log.info(new StringBuilder()
                        .append(operation.getType())
                        .append((operation.getType().equals(DEPOSIT) ? "       | " : "    | "))
                        .append(operation.getDate())
                        .append(" | ")
                        .append(operation.getAmount())
                        .append("       | ")
                        .append(operation.getBalance())
                        .append("\n")
                        .toString()));
        log.info("**********************************************************");
        log.info("BALANCE: {}", this.statement.getCurrentBalance());
    }

    private void throwExceptionWhenInValidAmount(BigDecimal newAmount, OperationType operationType) {
        if (newAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(AMOUNT_NOT_ALLOWED_MESSAGE);
        } else if (newAmount.compareTo(this.statement.getCurrentBalance()) > 0 && operationType.equals(WITHDRAWAL)) {
            throw new IllegalArgumentException(INSUFFICENT_FUND_MESSAGE);
        }
    }

    @Override
    public boolean doesAccountCharged(BigDecimal amountToWithDraw) {

        return this.statement.getCurrentBalance().equals(this.balanceBeforOperation.subtract(amountToWithDraw));
    }


}
