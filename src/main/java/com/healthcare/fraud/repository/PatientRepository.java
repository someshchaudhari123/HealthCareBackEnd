package com.healthcare.fraud.repository;

import com.healthcare.fraud.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {}