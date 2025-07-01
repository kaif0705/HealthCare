package com.kaif.healthcare.Payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecordDTO {

    private Long id;
    private Long patientId;

    private PatientDTO patientDTO;
    private String diagnose;

}
