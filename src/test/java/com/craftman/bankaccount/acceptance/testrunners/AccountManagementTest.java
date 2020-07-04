package com.craftman.bankaccount.acceptance.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/craftman/bankaccount/acceptance/features"},
        glue = {"com.craftman.bankaccount.acceptance.stepdefinitions"},
        plugin = {"pretty", "html:target/SystemTestReports/html.html"}
)
public class AccountManagementTest {

}
