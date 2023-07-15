package com.opus.assessment.service;

import com.opus.assessment.domain.Customer;
import com.opus.assessment.dto.CustomerDTO;

import java.util.concurrent.CompletableFuture;

public interface CustomerService {
    public CompletableFuture<CustomerDTO> validateCustomer(Customer customer);
    public CompletableFuture<CustomerDTO> validateCustomerAllOff(Customer customer);
}
