package com.kaif.healthcare.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy= "medicalRecord", fetch = FetchType.LAZY)
    private Patient patient;

    public MedicalRecord(String diagnose) {
        this.diagnose = diagnose;
    }

    private String diagnose;
}
