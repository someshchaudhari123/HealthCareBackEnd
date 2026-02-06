package com.healthcare.fraud.repository;

import com.healthcare.fraud.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    boolean existsByPatient_PatientIdAndTotalAmount(
            Long patientId,
            BigDecimal totalAmount
    );

    long countByPatient_PatientId(Long patientId);
}
