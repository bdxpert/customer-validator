package com.opus.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opus.assessment.domain.Address;
import com.opus.assessment.domain.Customer;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private long id;

    private String type;

    private String street;

    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Boolean valid = false;
    public static AddressDTO buildAddress(Address address, Boolean valid) {
        return builder()
                .id(address.getId())
                .street(address.getStreet())
                .state(address.getState())
                .valid(valid)
                .build();
    }
}
