package com.tinqinacademy.authenticationHotel.persistence.repository;

import com.tinqinacademy.authenticationHotel.persistence.entities.BlacklistedCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlacklistedCodesRepository extends JpaRepository<BlacklistedCodes, UUID> {
    boolean existsByCode(String code);

    Optional<BlacklistedCodes> findByCode(String code);

    @Transactional
    void deleteByEmail(String email);

}
