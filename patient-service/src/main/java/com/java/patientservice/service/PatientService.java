package com.java.patientservice.service;

import com.java.patientservice.DTO.PatientRequestDTO;
import com.java.patientservice.DTO.PatientResponseDTO;
import com.java.patientservice.exception.EmailAlreadyExistsException;
import com.java.patientservice.exception.PatientNotFoundException;
import com.java.patientservice.mapper.PatientMapper;
import com.java.patientservice.model.Patient;
import com.java.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

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

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient with this email "+patientRequestDTO.getEmail() + " already exists");
        }
//        var checkEmailAddress = patientRepository.findByEmail(patientRequestDTO.getEmail());
//        if (checkEmailAddress != null) {
//            throw new RuntimeException("Email already exists");
//        }
        Patient savedPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toPatientResponseDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(String id,
                                            PatientRequestDTO patientRequestDTO) {

        Patient patient = patientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient with this email "+patientRequestDTO.getEmail() + " already exists");
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDTO(updatedPatient);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(UUID.fromString(id));
    }
}
