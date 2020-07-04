package com.craftman.bankaccount.acceptance.stepdefinitions;

import com.craftman.bankaccount.Account;
import com.craftman.bankaccount.Operation;
import com.craftman.bankaccount.OperationType;
import com.craftman.bankaccount.Statement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementManagementSteps {
    public static final String DATE_FIELD_NAME = "date";
    public static final String OPERATION_TYPE_FIELD_NAME = "operationType";
    public static final String AMOUNT_FIELD_NAME = "amount";
    public static final String BALENCE_FIELD_NAME = "balance";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private Account account = new Account();

    @Given("I made the following operations in my account")
    public void i_made_the_following_operations_in_my_account(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> operationsMap = dataTable.asMaps(String.class, String.class);

        this.account = new Account();
        Statement statement = new Statement();

        statement.setOperations(this.operationsMapToOperations(operationsMap));
        this.account.setStatement(statement);
    }

    private List<Operation> operationsMapToOperations(final List<Map<String, String>> operationsMap) {
        return operationsMap.stream().map(this::cucumberMapToOperation).collect(Collectors.toList());
    }

    private Operation cucumberMapToOperation(final Map<String, String> map) {
        Operation operation = new Operation();
        operation.setType(OperationType.valueOf(map.get(OPERATION_TYPE_FIELD_NAME)));
        operation.setDate(LocalDateTime.parse(map.get(DATE_FIELD_NAME), formatter));
        operation.setAmount(new BigDecimal(map.get(AMOUNT_FIELD_NAME)));
        operation.setBalance(new BigDecimal(map.get(BALENCE_FIELD_NAME)));

        return operation;
    }

    @When("I see the history of my operations")
    public void i_see_the_history_of_my_operations() {
        System.out.println("Loading account statement...");
    }

    @Then("Account statement should display all operations histories")
    public void account_statement_should_display_all_operations_histories() {
        this.account.printHistory();
    }

    @Then("No operation history should be be displayed")
    public void no_operation_history_should_be_be_displayed() {
        Assert.assertTrue(this.account.getStatement().getOperations().isEmpty());
    }

}
