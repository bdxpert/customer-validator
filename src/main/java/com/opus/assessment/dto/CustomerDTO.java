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
public class CustomerDTO {
    private long id;
    private String name;
    private String phone;
    private String ssn;
    private SsnDTO ssnDTO;
    private AddressDTO address;

    public static CustomerDTO buildCustomer(Customer customer) {
        return builder()
                .id(customer.getId())
                .name(customer.getName())
                .ssn(customer.getSsn())
                .phone(customer.getPhone())
                .build();
    }

}
