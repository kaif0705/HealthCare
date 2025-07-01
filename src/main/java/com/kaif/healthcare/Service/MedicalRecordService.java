package com.kaif.healthcare.Service;

import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Payloads.MedicalRecordDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecord);
    MedicalRecordDTO getMedicalRecord(Long medicalRecordId);
    List<MedicalRecordDTO> getMedicalRecords();
    String deleteMedicalRecord(Long medicalRecordId);
    MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecordDTO);
}
