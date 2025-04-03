package com.java.patientservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String diagnosis;
    private String treatment;
    private String prescribedMedication;
    private LocalDate visitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;
}
