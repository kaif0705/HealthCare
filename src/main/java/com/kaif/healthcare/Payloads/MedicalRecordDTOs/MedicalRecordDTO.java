package com.kaif.healthcare.Payloads.MedicalRecordDTOs;

import com.kaif.healthcare.Payloads.PatientDTOs.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecordDTO {

    private Long id;
    private Long patientId;
    private String diagnose;

    //Object --> Non-owning side
    private PatientDTO patientDTO;

}
