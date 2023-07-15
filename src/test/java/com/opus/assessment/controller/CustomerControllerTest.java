package com.opus.assessment.controller;

import com.opus.assessment.dto.CustomerDTO;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;
    private static String validateCustomerUrl;
    private static String validateCustomerAllOfUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static JSONObject customerJsonObject;
    private static JSONObject addressJsonObject;
    private static HttpHeaders headers;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public  void runBeforeAllTestMethods() throws JSONException {
        validateCustomerUrl = "http://localhost:8080/customers/validate";
        validateCustomerAllOfUrl = "http://localhost:8080/customers/validate/with-all-of";

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        customerJsonObject = new JSONObject();
        customerJsonObject.put("id", 1);
        customerJsonObject.put("name", "1034895");
        customerJsonObject.put("ssn", "0000-000-9600");
        addressJsonObject = new JSONObject();
        addressJsonObject.put("street","1000 N 4 st");
        addressJsonObject.put("city","fairfield");
        addressJsonObject.put("state","iowa");
        addressJsonObject.put("zipCode","53446");
        addressJsonObject.put("country","USA");


        customerJsonObject.put("address", addressJsonObject);

    }
    @Test
    public void validateCustomer() throws Exception {
        HttpEntity<String> request = new HttpEntity<String>(customerJsonObject.toString(), headers);
        String customerResultAsJsonStr = restTemplate.postForObject(validateCustomerUrl, request, String.class);
        JsonNode root = objectMapper.readTree(customerResultAsJsonStr);

        CustomerDTO customer = restTemplate.postForObject(validateCustomerUrl, request, CustomerDTO.class);

        assertNotNull(customerResultAsJsonStr);
        assertNotNull(root);

        assertNotNull(customer);
        assertNotNull(customer.getName());
        assertTrue(customer.getSsnDTO().getValid().booleanValue());
        assertTrue(customer.getAddress().getValid().booleanValue());
    }

    @Test
    public void validateCustomerAllOf() throws Exception {
        HttpEntity<String> request = new HttpEntity<String>(customerJsonObject.toString(), headers);
        String customerResultAsJsonStr = restTemplate.postForObject(validateCustomerAllOfUrl, request, String.class);
        JsonNode root = objectMapper.readTree(customerResultAsJsonStr);

        CustomerDTO customer = restTemplate.postForObject(validateCustomerAllOfUrl, request, CustomerDTO.class);

        assertNotNull(customerResultAsJsonStr);
        assertNotNull(root);

        assertNotNull(customer);
        assertNotNull(customer.getName());
        assertTrue(customer.getSsnDTO().getValid().booleanValue());
        assertTrue(customer.getAddress().getValid().booleanValue());
    }

}
