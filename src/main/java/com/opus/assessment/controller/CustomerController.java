package com.opus.assessment.controller;

import com.opus.assessment.domain.Customer;
import com.opus.assessment.exception.APIRequestException;
import com.opus.assessment.service.CustomerService;
import com.opus.assessment.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateCustomer(@RequestBody Customer customer) {
        try {
            CompletableFuture<CustomerDTO> customerDTO = customerService.validateCustomer(customer);
            log.info("Customer validation successful");
            return new ResponseEntity<CustomerDTO>(customerDTO.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIRequestException("Unable to process customer validation" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/validate/with-all-of")
    public ResponseEntity<?> validateCustomerAllOf(@RequestBody Customer customer) {
        try {
            CompletableFuture<CustomerDTO> customerDTO = customerService.validateCustomerAllOff(customer);

            log.info("Customer validation successful");
            return new ResponseEntity<CustomerDTO>(customerDTO.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIRequestException("Unable to process customer validation" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
