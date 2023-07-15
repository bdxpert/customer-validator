package com.opus.assessment.client;

import com.opus.assessment.domain.Customer;
import com.opus.assessment.dto.SsnDTO;
import com.opus.assessment.utils.DataLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class SSNValidator {
    private final DataLoader dataLoader;

    public Optional<SsnDTO> validateSSN(Customer customer) {
        log.info("Search SSN: {}", customer.getSsn());
        if( dataLoader.getFakeSSN().contains(customer.getSsn())) {
            return Optional.of(SsnDTO.buildSSN(customer, true));
        } else {
            return Optional.of(SsnDTO.buildSSN(customer, false));
        }
    }
}
