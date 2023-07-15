package com.opus.assessment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
//@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
*/
    private long id;

//    private String type;

    private String street;

    private String city;
    private String state;
    private String zipCode;
    private String country;
    /*@JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;*/
}
