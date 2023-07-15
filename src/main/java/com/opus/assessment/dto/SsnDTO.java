package com.opus.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opus.assessment.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SsnDTO {
    String ssn;
    Boolean valid;
    public static SsnDTO buildSSN(Customer customer, Boolean valid) {
        return builder()
                .ssn(customer.getSsn())
                .valid(valid)
                .build();
    }
}
