package com.kaif.healthcare.Payloads;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Model.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private Gender gender;
    private String email;
    private int age;
    private Address patientAddress;
    private List<PrescriptionDTO> prescriptionDTO= new ArrayList<>();

    private MedicalRecord medicalRecord;
    private DoctorDTO doctor;

}
