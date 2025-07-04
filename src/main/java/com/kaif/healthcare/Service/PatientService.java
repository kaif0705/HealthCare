package com.kaif.healthcare.Service;

import com.kaif.healthcare.Payloads.PatientDTOs.CreatePatientDTO;
import com.kaif.healthcare.Payloads.PatientDTOs.PatientDTO;
import com.kaif.healthcare.Payloads.PatientDTOs.UpdatePatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO getPatientById(Long patientId);

    //Create
    CreatePatientDTO addPatient(CreatePatientDTO patientDTO, Long doctorId);
    String deletePatient(Long patientId);
    String updatePatient(Long patientId, UpdatePatientDTO patientDTO);
}
