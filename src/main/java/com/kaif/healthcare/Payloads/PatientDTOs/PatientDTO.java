package com.kaif.healthcare.Payloads.PatientDTOs;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.ManyToMany.PrescriptionDetailsDTO;
import com.kaif.healthcare.Model.Address;
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

    //ID because owning side of F.K
    private Long medicalRecordId;

    //ID because owning side of F.K
    private Long doctorID;

    //Object --> Non-owning side
    private List<PrescriptionDetailsDTO> prescriptionDetailsDTO = new ArrayList<>();


}
