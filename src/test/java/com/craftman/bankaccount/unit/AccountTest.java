package com.craftman.bankaccount.unit;

import com.craftman.bankaccount.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final BigDecimal CORRECT_DEPOSIT_AMOUNT = BigDecimal.TEN;
    private static final BigDecimal INCORRECT_DEPOSIT_AMOUNT = BigDecimal.ZERO;
    private static final BigDecimal CORRECT_WITHDRAWAL_AMOUNT = new BigDecimal(5);
    private Account account = new Account();

    @Test
    public void givenCorrectAmountShouldMakeDeposit() {
        this.account.deposit(CORRECT_DEPOSIT_AMOUNT);
        assertNotNull(this.account.getStatement().getCurrentBalance());
        assertNotEquals(this.account.getStatement().getCurrentBalance(), BigDecimal.ZERO);
        assertEquals(CORRECT_DEPOSIT_AMOUNT, this.account.getStatement().getCurrentBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenIncorrectAmountShouldThrowIllegalArgumentException() {
        this.account.deposit(INCORRECT_DEPOSIT_AMOUNT);
        BigDecimal expectedBalance = BigDecimal.ZERO;
        assertEquals(expectedBalance, this.account.getStatement().getCurrentBalance());
    }

    @Test
    public void givenCorrectAmountShouldMakeWithdrawal() {
        this.account.deposit(CORRECT_DEPOSIT_AMOUNT);
        this.account.deposit(BigDecimal.ONE);

        this.account.withDraw(CORRECT_WITHDRAWAL_AMOUNT);
        BigDecimal expectedBalance = new BigDecimal(6);
        assertEquals(expectedBalance, this.account.getStatement().getCurrentBalance());
    }

}
