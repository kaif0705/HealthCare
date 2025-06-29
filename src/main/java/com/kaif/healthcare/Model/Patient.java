package com.kaif.healthcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaif.healthcare.Emuns.Gender;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Patient extends Person {

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private int age;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Transient
    private String ageGroup;

    @Embedded
    @Valid
    private Address patientAddress;

    public Patient(String name, int age) {
//        this.name = name;
//        this.age = age;
        //Empty address instance to get it through patient instance while setting the address
        patientAddress = new Address();
        this.ageGroup = calculateAgeGroup(age);
    }

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name= "medical_record_id")
    @Valid
    private MedicalRecord medicalRecord;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name= "doctor_id")
    @Valid
    private Doctor doctor;

    @OneToMany(mappedBy = "prescription", fetch= FetchType.LAZY)
    @JsonIgnore
    private List<Prescription> prescription= new ArrayList<>();

    private String calculateAgeGroup(int age){
        if(age<12){
            return "Child";
        }else if(age<=19){
            return "Teen";
        }else if(age <= 59){
            return "Adult";
        }else{
            return "Senior";
        }
    }

}
