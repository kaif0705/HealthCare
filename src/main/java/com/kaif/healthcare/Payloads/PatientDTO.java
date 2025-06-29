package com.kaif.healthcare.Payloads;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Model.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private String name;
    private Gender gender;
    private String email;
    private int age;
    private Address patientAddress;
    private List<Prescription> prescription= new ArrayList<>();
    private MedicalRecord medicalRecord;
    private Doctor doctor;

}
