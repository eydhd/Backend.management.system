package com.example.end.repository;

import com.example.end.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
    Optional<Admin> findByMobilePhoneNumber(String mobilePhoneNumber);
    boolean existsByName(String name);
    boolean existsByMobilePhoneNumber(String mobilePhoneNumber);
}