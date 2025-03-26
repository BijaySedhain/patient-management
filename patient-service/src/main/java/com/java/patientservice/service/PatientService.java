package com.java.patientservice.service;

import com.java.patientservice.DTO.PatientRequestDTO;
import com.java.patientservice.DTO.PatientResponseDTO;
import com.java.patientservice.mapper.PatientMapper;
import com.java.patientservice.model.Patient;
import com.java.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toPatientResponseDTO).toList();
    }

    public PatientResponseDTO getPatientById(String id) {
        return patientRepository.findById(UUID.fromString(id))
                .map(PatientMapper::toPatientResponseDTO)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient savedPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toPatientResponseDTO(savedPatient);
    }
}
