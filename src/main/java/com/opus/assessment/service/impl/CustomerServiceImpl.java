package com.opus.assessment.service.impl;


import com.opus.assessment.client.AddressValidator;
import com.opus.assessment.client.CustomerClient;
import com.opus.assessment.client.SSNValidator;
import com.opus.assessment.domain.Customer;
import com.opus.assessment.dto.AddressDTO;
import com.opus.assessment.dto.SsnDTO;

import com.opus.assessment.service.CustomerService;
import com.opus.assessment.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;



@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerClient customerClient;
    private AddressValidator addressValidator;
    private SSNValidator ssnValidator;

    public CustomerServiceImpl(CustomerClient customerClient, AddressValidator addressValidator, SSNValidator ssnValidator) {
        this.customerClient = customerClient;
        this.addressValidator = addressValidator;
        this.ssnValidator = ssnValidator;
    }

    @Override
    public CompletableFuture<CustomerDTO> validateCustomer(Customer customer) {
        // fetch client
        // 1. if it is already exist in bd pic that one
        // 2. else fetch from api call
        CompletableFuture<Optional<CustomerDTO>> customerResponseCFMy = CompletableFuture.supplyAsync(
                () -> customerClient.findCustomer(customer)
        );

        CompletableFuture<AddressDTO> addressResponseCF = CompletableFuture.supplyAsync(
                () -> addressValidator.findAddress(customer.getAddress()).get());
        CompletableFuture<SsnDTO> ssnResponseCF = CompletableFuture.supplyAsync(
                () -> ssnValidator.validateSSN(customer).get());

        CompletableFuture<CustomerDTO> response = customerResponseCFMy.thenCombine(addressResponseCF, (customerRes, addressRes) -> {
            customerRes.ifPresent(cus -> cus.setAddress(addressRes));
            return customerRes;
        }).thenCombine(ssnResponseCF, (customerRes, ssnRes)->{
            customerRes.ifPresent(cus->cus.setSsnDTO(ssnRes));
            return customerRes;
        })
        .thenApply(customerRes -> customerRes.orElse(null));

        return response;
    }

    @Override
    public CompletableFuture<CustomerDTO> validateCustomerAllOff(Customer customer) {
        // fetch client
        // 1. if it is already exist in bd pic that one
        // 2. else fetch from api call
        CompletableFuture<Optional<CustomerDTO>> customerResponseCFMy = CompletableFuture.supplyAsync(
                () -> customerClient.findCustomer(customer)
        );

        CompletableFuture<AddressDTO> addressResponseCF = CompletableFuture.supplyAsync(
                () -> addressValidator.findAddress(customer.getAddress()).get());
        CompletableFuture<SsnDTO> ssnResponseCF = CompletableFuture.supplyAsync(
                () -> ssnValidator.validateSSN(customer).get());
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(
                customerResponseCFMy, addressResponseCF,
                ssnResponseCF);

        Optional<CustomerDTO> customerResponseOptional = customerResponseCFMy.join();

        CompletableFuture<CustomerDTO> responseCF = voidCompletableFuture
                .thenApply(unusedVariable -> {
                            customerResponseOptional.ifPresent(cr -> {
                                AddressDTO addressResponse = addressResponseCF.join();
                                cr.setAddress(addressResponse);
                                SsnDTO ssnRes=ssnResponseCF.join();
                                cr.setSsnDTO(ssnRes);
                            });
                            return customerResponseOptional
                                    .orElse(null);
                        }
                );
        return responseCF;
    }

}
