package com.java.patientservice.mapper;

import com.java.patientservice.DTO.MedicalRecordDTO;
import com.java.patientservice.model.MedicalRecord;

public class MedicalRecordMapper {
    public static MedicalRecordDTO toMedicalRecordDTO(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setDiagnosis(medicalRecord.getDiagnosis());
        medicalRecordDTO.setTreatment(medicalRecord.getTreatment());
        medicalRecordDTO.setPrescribedMedication(medicalRecord.getPrescribedMedication());
        medicalRecordDTO.setVisitDate(medicalRecord.getVisitDate());
        return medicalRecordDTO;
    }
}
