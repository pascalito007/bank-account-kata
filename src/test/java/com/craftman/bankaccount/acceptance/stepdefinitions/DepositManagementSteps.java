package com.craftman.bankaccount.acceptance.stepdefinitions;

import com.craftman.bankaccount.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class DepositManagementSteps {

    BigDecimal newAmount;
    Account account = new Account();
    String errorMessage;

    @Given("I have amount {int}")
    public void i_have_amount(Integer newAmount) {
        this.newAmount = new BigDecimal(newAmount);
    }

    @When("I add that amount in my account")
    public void i_add_that_amount_in_my_account() {
        try {
            this.account.deposit(this.newAmount);
        } catch (IllegalArgumentException e) {
            this.errorMessage = e.getMessage();
        }
    }

    @Then("my bank account with amount {int} should be credited")
    public void my_bank_account_with_amount_should_be_credited(Integer newAmount) {
        boolean isAmountAdded = this.account.doesNewAmountAdded(this.newAmount);
        assertTrue(isAmountAdded);
    }

    @Then("I should see an error message with {string}")
    public void i_should_see_an_error_message_with(String errorMessage) {
        Assert.assertEquals("Amount not allowed", errorMessage);
    }

}
