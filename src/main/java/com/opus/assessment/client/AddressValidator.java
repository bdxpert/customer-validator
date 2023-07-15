package com.opus.assessment.client;

import com.opus.assessment.domain.Address;
import com.opus.assessment.dto.AddressDTO;
import com.opus.assessment.utils.DataLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressValidator {
    private final DataLoader dataLoader;

    public Optional<AddressDTO> findAddress(Address address) {
        log.info("Getting address {}", address.toString());
         if(dataLoader.getFakeAddress().stream().
                anyMatch(addr->
                         addr.getStreet().toLowerCase().equals(address.getStreet().toLowerCase())
                        && addr.getCity().toLowerCase().equals(address.getCity().toLowerCase())
                        && addr.getState().toLowerCase().equals(address.getState().toLowerCase())
                        && addr.getZipCode().toLowerCase().equals(address.getZipCode().toLowerCase())
                        && addr.getCountry().toLowerCase().equals(address.getCountry().toLowerCase())
                    )) {
             return Optional.of(AddressDTO.buildAddress(address, true));
         } else {
             return Optional.of(AddressDTO.buildAddress(address, false));
         }
    }
}
