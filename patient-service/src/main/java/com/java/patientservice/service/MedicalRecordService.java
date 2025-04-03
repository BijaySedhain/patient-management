package com.java.patientservice.service;

import com.java.patientservice.DTO.MedicalRecordDTO;
import com.java.patientservice.exception.MedicalRecordNotFoundException;
import com.java.patientservice.model.MedicalRecord;
import com.java.patientservice.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.java.patientservice.mapper.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecordDTO> getAllMedicalRecords() {

        log.info("Fetching all the medical records");
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        if (medicalRecords.isEmpty()) {
            log.warn("No medical records found");
            return List.of();
        }

        log.info("Found {} medical records", medicalRecords.size());
        return medicalRecords.stream().map(MedicalRecordMapper::toMedicalRecordDTO).toList();
    }

    public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setTreatment(medicalRecordDTO.getTreatment());
        medicalRecord.setPrescribedMedication(medicalRecordDTO.getPrescribedMedication());
        medicalRecord.setVisitDate(medicalRecordDTO.getVisitDate());

        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        log.info("Medical Record created: {}", savedMedicalRecord);
        return MedicalRecordMapper.toMedicalRecordDTO(savedMedicalRecord);
    }

    public MedicalRecordDTO updateMedicalRecord(String id, MedicalRecordDTO medicalRecordDTO) {

        UUID uuid = UUID.fromString(id);

        MedicalRecord medicalRecord = medicalRecordRepository.findById(uuid)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical record not found with id: " + id));

        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setTreatment(medicalRecordDTO.getTreatment());
        medicalRecord.setPrescribedMedication(medicalRecordDTO.getPrescribedMedication());
        medicalRecord.setVisitDate(medicalRecordDTO.getVisitDate());

        MedicalRecord updatedMedicalRecord = medicalRecordRepository.save(medicalRecord);

        log.info("Medical record with ID: {} updated successfully", id);
        return MedicalRecordMapper.toMedicalRecordDTO(updatedMedicalRecord);
    }

    public void deleteMedicalRecord(String id) {
        UUID uuid = UUID.fromString(id);
        medicalRecordRepository.deleteById(uuid);
        log.info("Medical record with ID: {} deleted successfully", id);
    }
}
