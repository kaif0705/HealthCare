package com.kaif.healthcare.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @Embedded
    private Address patientAddress;

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        //Empty address instance to get it through patient instance while setting the address
        patientAddress = new Address();
    }

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name= "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name= "doctor_id")
    private Doctor doctor;

}
