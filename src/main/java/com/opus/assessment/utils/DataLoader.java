package com.opus.assessment.utils;

import com.github.javafaker.Faker;
import com.opus.assessment.domain.Address;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
@RequiredArgsConstructor
public class DataLoader {

    private Set<String> fakeSSN = new HashSet<>();
    private List<Address> fakeAddress = new ArrayList<>();


    @Bean
    private InitializingBean fakeDataBuilder() {
        Faker faker = new Faker();
        return () -> {
            generateMockSSN(faker);
            fakeAddress.add( generateMockAddress(faker));
            fakeAddress.add(generateMockAddressV2(faker));
        };
    }
    private Address generateMockAddress(Faker faker) {
        return  Address.builder()
                .id(1L)
                .street(faker.address().streetName())
                .city(faker.address().city())
                .state(faker.address().state())
                .zipCode(faker.address().zipCode())
                .country(faker.address().country())
                .build();
    }
    private Address generateMockAddressV2(Faker faker) {
        return  Address.builder()
                .id(2L)
                .street("1000 N 4 st")
                .city("fairfield")
                .state("iowa")
                .zipCode("53446")
                .country("USA")
                .build();
    }
    private Set<String> generateMockSSN(Faker faker) {
        fakeSSN.add("0000-000-9600");
        fakeSSN.add("0000-000-9500");
        fakeSSN.add("0000-000-9400");
        fakeSSN.add("0000-000-9300");
        fakeSSN.add("0000-000-9200");
        fakeSSN.add("0000-000-9100");
        return  fakeSSN;
    }
}
