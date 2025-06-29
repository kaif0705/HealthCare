package com.kaif.healthcare.Payloads;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Model.Patient;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;
    private Address doctorAddress;
    private String speciality;
    private List<PatientDTO> patients= new ArrayList<>();

}
