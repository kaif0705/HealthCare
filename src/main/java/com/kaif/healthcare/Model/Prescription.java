package com.kaif.healthcare.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@IdClass(PrescriptionId.class) --> requires strict matching of Id
public class Prescription {

    @EmbeddedId // <--- Use @EmbeddedId to embed the composite key class
    private PrescriptionId id;

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
    private String prescription;

    @MapsId("doctorId")
    @ManyToOne(fetch= FetchType.LAZY, optional= false)
    @JoinColumn(name= "doctor_id")
    private Doctor doctor;

    @MapsId("patientId")
    @ManyToOne(fetch= FetchType.LAZY, optional= false)
    @JoinColumn(name= "patient_id")
    private Patient patient;

    public Prescription(String prescription){
        this.prescription= prescription;
    }

    @ManyToMany
    @JoinTable(
            joinColumns= {
                    @JoinColumn(name= "prescription_doctor_id", referencedColumnName = "doctor_id"),
                    @JoinColumn(name= "presrciption_patient_id", referencedColumnName= "patient_id")
            },
            inverseJoinColumns= @JoinColumn(name= "medicine_id")
    )
    private List<Medicine> medicine= new ArrayList<>();


}
