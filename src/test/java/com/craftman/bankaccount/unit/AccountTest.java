package com.craftman.bankaccount.unit;

import com.craftman.bankaccount.Account;
import com.craftman.bankaccount.Operation;
import com.craftman.bankaccount.Statement;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.craftman.bankaccount.OperationType.DEPOSIT;
import static com.craftman.bankaccount.OperationType.WITHDRAWAL;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final BigDecimal VALID_DEPOSIT_AMOUNT = BigDecimal.TEN;
    private static final BigDecimal INVALID_DEPOSIT_AMOUNT = BigDecimal.ZERO;
    private static final BigDecimal VALID_WITHDRAWAL_AMOUNT = new BigDecimal(5);

    @Mock
    Statement statement;
    @InjectMocks
    Account account;

    @Test
    public void given_valid_mount_should_make_deposit() {
        //ARRANGE
        BigDecimal balance = BigDecimal.TEN;
        given(this.account.getStatement().getCurrentBalance()).willReturn(balance);

        //ACT
        this.account.deposit(VALID_DEPOSIT_AMOUNT);

        //ASSERT
        verify(this.account.getStatement(), atMost(1)).recordTransaction(VALID_DEPOSIT_AMOUNT, DEPOSIT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_invalid_amount_when_deposit_should_throw_IllegalArgumentException() {
        //ACT
        this.account.deposit(INVALID_DEPOSIT_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_invalid_amount_when_withdrawal_should_throw_IllegalArgumentException() {
        //ACT
        this.account.withDraw(INVALID_DEPOSIT_AMOUNT);
    }

    @Test
    public void given_valid_amount_should_make_withdrawal() {
        //ARRANGE
        BigDecimal currentBalance = BigDecimal.TEN;
        given(this.account.getStatement().getCurrentBalance()).willReturn(currentBalance);

        //ACT
        this.account.withDraw(VALID_WITHDRAWAL_AMOUNT);

        //ASSERT
        verify(this.account.getStatement(), atMost(1)).recordTransaction(VALID_WITHDRAWAL_AMOUNT, WITHDRAWAL);
    }

    @Test
    public void given_operations_should_print_history() {
        //ARRANGE
        BigDecimal currentBalance = new BigDecimal(100);

        given(this.account.getStatement().getCurrentBalance()).willReturn(currentBalance);
        given(this.account.getStatement().getOperations()).willReturn(this.getTransactions());

        //ACT
        this.account.printHistory();

        //ASSERT
        LocalDateTime operationTime = LocalDateTime.parse("20/07/2020 12:12:21", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        Assertions.assertThat(this.account.getStatement().getOperations())
                .hasSize(1)
                .extracting(Operation::getAmount, Operation::getBalance, Operation::getType, Operation::getDate)
                .containsExactly(
                        tuple(new BigDecimal(100), new BigDecimal(100), DEPOSIT, operationTime)
                );

    }

    private List<Operation> getTransactions() {
        List<Operation> operations = new ArrayList<>();
        Operation operation = new Operation();
        operation.setBalance(new BigDecimal(100));
        operation.setAmount(new BigDecimal(100));
        operation.setType(DEPOSIT);
        operation.setDate(LocalDateTime.parse("20/07/2020 12:12:21", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        operations.add(operation);
        return operations;
    }

}
