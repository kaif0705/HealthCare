package com.kaif.healthcare.Model;

import com.kaif.healthcare.Emuns.Gender;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    private String ageGroup;

    @Embedded
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
    private MedicalRecord medicalRecord;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name= "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "prescription", fetch= FetchType.LAZY)
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
