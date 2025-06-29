package com.kaif.healthcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @JsonIgnore
    private Patient patient;

    public MedicalRecord(String diagnose) {
        this.diagnose = diagnose;
    }

    @NotBlank(message= "Diagnose cannot be blank")
    private String diagnose;
}
