package com.example.end.repository;

import com.example.end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByMobilePhoneNumber(String mobilePhoneNumber);
    boolean existsByName(String name);
    boolean existsByMobilePhoneNumber(String mobilePhoneNumber);
}