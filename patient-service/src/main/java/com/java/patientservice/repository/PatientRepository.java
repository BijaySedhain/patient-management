package com.java.patientservice.repository;

import com.java.patientservice.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Patient findByEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email);
    boolean existsByEmail(String email);
}
