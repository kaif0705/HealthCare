package com.kaif.healthcare.Model;

import jakarta.persistence.*;
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
    private Address doctorAddress;

    private String speciality;

    public Doctor(String doctorName){
//        this.doctorName = doctorName;
//        doctorAddress= new Address();
    }

    @OneToMany(mappedBy= "doctor")
    private List <Patient> patients= new ArrayList<>();

}
