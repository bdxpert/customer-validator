package com.opus.assessment.junit.service;

import com.opus.assessment.client.AddressValidator;
import com.opus.assessment.client.CustomerClient;
import com.opus.assessment.client.SSNValidator;
import com.opus.assessment.domain.Address;
import com.opus.assessment.domain.Customer;
import com.opus.assessment.dto.CustomerDTO;
import com.opus.assessment.service.CustomerService;
import com.opus.assessment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

//https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private  CustomerClient customerClient;
    @Autowired
    private  AddressValidator addressValidator;
    @Autowired
    private  SSNValidator ssnValidator;


    private static Customer customer;
    private static Address address;


    @BeforeEach
    public void setup() {


        //customerService = new CustomerServiceImpl(customerClient, addressValidator, ssnValidator);

        customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer 1");
        customer.setSsn("0000-000-9600");

        address = new Address();
        address.setId(2l);
        address.setStreet("1000 N 4 st");
        address.setCity("fairfield");
        address.setState("iowa");
        address.setZipCode("53446");
        address.setCountry("USA");
        customer.setAddress(address);

    }

    @DisplayName("Service class Test: customer validation for ssn and address")
    @Test
    void doValidation() throws Exception {
        CompletableFuture<CustomerDTO> customerDTO = customerService.validateCustomer(customer);
        assertTrue(customerDTO.get().getSsnDTO().getValid());
        assertTrue(customerDTO.get().getAddress().getValid());
    }

}
