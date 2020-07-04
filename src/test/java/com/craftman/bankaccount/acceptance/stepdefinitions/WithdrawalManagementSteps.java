package com.craftman.bankaccount.acceptance.stepdefinitions;

import com.craftman.bankaccount.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WithdrawalManagementSteps {
    private BigDecimal amountToWithDraw;
    Account account = new Account();
    String errorMessage;
    List<BigDecimal> savings;

    @Given("I have saved the following amounts in my account")
    public void i_have_saved_the_following_amounts_in_my_account(io.cucumber.datatable.DataTable dataTable) {
        List<BigDecimal> savings = this.savings = dataTable.asList(BigDecimal.class);
        savings.forEach(this.account::deposit);
    }

    @When("I withdraw amount {int} from my account")
    public void i_withdraw_amount_from_my_account(Integer amountToWithdraw) {
        this.amountToWithDraw = new BigDecimal(amountToWithdraw);
        try {
            account.withDraw(this.amountToWithDraw);
        } catch (IllegalArgumentException e) {
            this.errorMessage = e.getMessage();
        }
    }

    @Then("my bank account with amount {int} should be charged")
    public void my_bank_account_with_amount_should_be_charged(Integer int1) {
        boolean isAmountRetrived = this.account.doesAccountCharged(this.amountToWithDraw);
        assertTrue(isAmountRetrived);
    }

    @Then("my bank account with balance {int} should be remained")
    public void my_bank_account_balance_should_be_remained(Integer balance) {
        assertEquals(BigDecimal.ZERO, this.account.getStatement().getCurrentBalance());
    }


    @Then("I should see another error message with {string}")
    public void iShouldSeeAnotherErrorMessageWith(String errorMessage) {
        assertEquals("Insufficient funds to retrieve", this.errorMessage);
    }
}
