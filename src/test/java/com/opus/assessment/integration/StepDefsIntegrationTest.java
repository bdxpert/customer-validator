package com.opus.assessment.integration;

import com.opus.assessment.dto.CustomerDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.cucumber.messages.internal.com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {

    @When("^the client calls /customers/validate$")
    public void the_client_issues_POST_valid() throws Throwable {
        customerValidation();
    }


    @When("^the client calls /customers/validate/with-all-of$")
    public void the_client_issues_POST_valid_v2() throws Throwable {
        executeCustomerValidationAllOf();
    }
    /*
    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of_v2(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = (HttpStatus) latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

     */

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = (HttpStatus) latestResponse.getTheResponse().getStatusCode();

        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));

    }
    @And("^the client get validation status (.+)$")
    public void the_client_receives_server_version_body(Boolean status) throws Throwable {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(latestResponse.getBody());// response will be the json String
        CustomerDTO customerDTO = gson.fromJson(object, CustomerDTO.class);

        assertThat("ssn validation is incorrect (ssn, value): (" + customerDTO.getSsn()+","+customerDTO.getSsnDTO().getValid()+")",
                customerDTO.getSsnDTO().getValid(), is(status));
        assertThat("Address validation is incorrect (address, value): (" + customerDTO.getAddress().toString()+","+customerDTO.getAddress().getValid()+")",
                customerDTO.getAddress().getValid(), is(status));

    }
}
