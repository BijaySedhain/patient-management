package com.java.patientservice;

import com.java.patientservice.DTO.PatientRequestDTO;
import com.java.patientservice.DTO.PatientResponseDTO;
import com.java.patientservice.model.Patient;
import com.java.patientservice.repository.PatientRepository;
import com.java.patientservice.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PatientServiceApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private PatientRequestDTO patientRequestDTO;

    @BeforeEach
    void setUp(){
        patient = new Patient();
        patient.setId(UUID.randomUUID());
        patient.setName("John Doe");
        patient.setEmail("john.doe@example.com");
        patient.setAddress("123 Main St");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));

        patientRequestDTO = new PatientRequestDTO();
        patientRequestDTO.setName("John Doe");
        patientRequestDTO.setEmail("john.doe@example.com");
        patientRequestDTO.setAddress("123 Main St");
        patientRequestDTO.setDateOfBirth("1990-01-01");
        patientRequestDTO.setRegisteredDate("2023-01-01");
    }


    @Test
    void testGetAllPatients(){
        when(patientRepository.findAll()).thenReturn(List.of(patient));
        List<PatientResponseDTO> patientResponseDTOS = patientService.getAllPatients();
        assertEquals(1, patientResponseDTOS.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testGetPatientById(){
        when(patientRepository.findById(patient.getId())).thenReturn(java.util.Optional.of(patient));
        PatientResponseDTO patientResponseDTO = patientService.getPatientById(patient.getId().toString());
        assertEquals(patient.getName(), patientResponseDTO.getName());
        assertEquals(patient.getEmail(), patientResponseDTO.getEmail());
        assertEquals(patient.getAddress(), patientResponseDTO.getAddress());
        assertEquals(patient.getDateOfBirth(), LocalDate.parse(patientResponseDTO.getDateOfBirth()));
        verify(patientRepository, times(1)).findById(patient.getId());
    }
}
