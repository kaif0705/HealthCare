package com.kaif.healthcare.Payloads.DoctorDTOs;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Payloads.PatientDTOs.PatientDTO;
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
    private String email;
    private String speciality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    //Object  --> Embedded Object
    private Address doctorAddress;

    //Object --> Non-owning side
    private List<PatientDTO> patients= new ArrayList<>();

}
