package com.java.patientservice.controller;

import com.java.patientservice.DTO.MedicalRecordDTO;
import com.java.patientservice.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medical-records")
@Tag(name = "Medical Records", description = "Medical Records API")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping()
    @Operation(summary = "Get all the medical records")
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {
        List<MedicalRecordDTO> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok().body(medicalRecords);
    }

    @PostMapping
    @Operation(summary = "Create new medical record")
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO savedMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecordDTO);
        return ResponseEntity.ok().body(savedMedicalRecord);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update medical record")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@PathVariable String id,
                                                                 MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO updatedMedicalRecord = medicalRecordService.updateMedicalRecord(id, medicalRecordDTO);
        return ResponseEntity.ok().body(updatedMedicalRecord);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete medical record")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable String id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.ok().body("Medical record deleted successfully");
    }
}
