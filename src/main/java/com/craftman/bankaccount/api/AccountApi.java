package com.craftman.bankaccount.api;

import java.math.BigDecimal;

public interface AccountApi {
    void deposit(BigDecimal amount);

    boolean doesNewAmountAdded(BigDecimal newAmount);

    void withDraw(BigDecimal amountToWithDraw);

    void printHistory();

    boolean doesAccountCharged(BigDecimal amountToWithDraw);
}
