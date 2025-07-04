package com.kaif.healthcare.Payloads.PatientDTOs;

import com.kaif.healthcare.Model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private Address patientAddress;
}
