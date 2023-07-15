package com.opus.assessment.repository;

import com.opus.assessment.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
 Optional<Customer> findBySsn(String ssn);
}
