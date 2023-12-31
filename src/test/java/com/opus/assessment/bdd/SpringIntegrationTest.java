package com.opus.assessment.bdd;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.opus.assessment.CustomerValidatorApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest(classes = CustomerValidatorApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static ResponseResults latestResponse = null;

    protected RestTemplate restTemplate;

    void customerValidation() throws IOException, JSONException{

        JSONObject customerJsonObject = new JSONObject();
        customerJsonObject.put("id", 1);
        customerJsonObject.put("name", "San Sar");
        customerJsonObject.put("ssn", "0000-000-9600");

        JSONObject addressJsonObject = new JSONObject();
        addressJsonObject.put("street","1000 N 4 st");
        addressJsonObject.put("city","fairfield");
        addressJsonObject.put("state","iowa");
        addressJsonObject.put("zipCode","53446");
        addressJsonObject.put("country","USA");


        customerJsonObject.put("address", addressJsonObject);

        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);

        requestCallback.setBody(customerJsonObject.toString());

        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);

        latestResponse = restTemplate
                .execute("http://localhost:8080/customers/validate", HttpMethod.POST, requestCallback, response -> {
                    if (errorHandler.hadError) {
                        return (errorHandler.getResults());
                    } else {
                        return (new ResponseResults(response));
                    }
                });
    }

    void executeCustomerValidationAllOf() throws IOException, JSONException {

        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        JSONObject customerJsonObject = new JSONObject();
        customerJsonObject.put("id", 1);
        customerJsonObject.put("name", "1034895");
        customerJsonObject.put("ssn", "0000-000-9600");

        JSONObject addressJsonObject = new JSONObject();
        addressJsonObject.put("street","1000 N 4 st");
        addressJsonObject.put("city","fairfield");
        addressJsonObject.put("state","iowa");
        addressJsonObject.put("zipCode","53446");
        addressJsonObject.put("country","USA");


        customerJsonObject.put("address", addressJsonObject);

        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);


        requestCallback.setBody(customerJsonObject.toString());

        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);

        latestResponse = restTemplate
                .execute("http://localhost:8080/customers/validate/with-all-of", HttpMethod.POST, requestCallback, response -> {
                    if (errorHandler.hadError) {
                        return (errorHandler.getResults());
                    } else {
                        return (new ResponseResults(response));
                    }
                });
    }
    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
}
