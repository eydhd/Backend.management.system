package com.example.end.repository;

import com.example.end.entity.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {
    Optional<Firm> findByName(String name);
    Optional<Firm> findByMobilePhoneNumber(String mobilePhoneNumber);
    boolean existsByName(String name);
    boolean existsByMobilePhoneNumber(String mobilePhoneNumber);
}