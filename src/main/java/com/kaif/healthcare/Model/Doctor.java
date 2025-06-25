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
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy= "doctor")
    private List <Patient> patients= new ArrayList<>();

    private String doctorName;

    public Doctor(String doctorName){
        this.doctorName = doctorName;
    }

}
