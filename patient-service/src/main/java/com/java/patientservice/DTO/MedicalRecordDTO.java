package com.java.patientservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO {
    private String diagnosis;
    private String treatment;
    private String prescribedMedication;
    private LocalDate visitDate;
}
