package com.java.patientservice.controller;

import com.java.patientservice.DTO.PatientRequestDTO;
import com.java.patientservice.DTO.PatientResponseDTO;
import com.java.patientservice.model.Patient;
import com.java.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String id) {
        PatientResponseDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody PatientRequestDTO patient) {
        PatientResponseDTO savedPatient = patientService.createPatient(patient);
        return ResponseEntity.ok().body(savedPatient);
    }
}
