package com.opus.assessment.client;

import com.opus.assessment.domain.Customer;
import com.opus.assessment.dto.CustomerDTO;
import com.opus.assessment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerClient {
    private CustomerRepository customerRepository;

    public CustomerClient(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public  Optional<CustomerDTO> findCustomer(Customer customer)
    {
//        Optional<Customer> existingCustomer = customerRepository.findBySsn(customer.getSsn());
//        if(!existingCustomer.isPresent())
            return Optional.of(CustomerDTO.buildCustomer(customer));
//        return Optional.of(CustomerDTO.buildCustomer(existingCustomer.get()));
    }
}
