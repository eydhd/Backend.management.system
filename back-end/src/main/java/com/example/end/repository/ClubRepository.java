package com.example.end.repository;

import com.example.end.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByName(String name);
    boolean existsByName(String name);
    boolean existsByMobilePhoneNumber(String mobilePhoneNumber);
}