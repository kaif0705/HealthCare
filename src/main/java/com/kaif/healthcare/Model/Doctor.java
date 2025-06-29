package com.kaif.healthcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor extends Person {

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//    private String doctorName;

    @Embedded
    @Valid
    private Address doctorAddress;

    @NotBlank(message= "Doctors Speciality cannot be blank")
    @Size(max = 10, message = "Speciality cannot exceed 10 characters")
    private String speciality;

    public Doctor(String doctorName){
//        this.doctorName = doctorName;
//        doctorAddress= new Address();
    }

    @OneToMany(mappedBy= "doctor")
    @JsonIgnore
    private List <Patient> patients= new ArrayList<>();

}
