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
//@IdClass(PrescriptionId.class)// --> requires strict matching of Id
public class Prescription {

    @EmbeddedId //used when our entity has more than 2 fields as P.K
    //Object of PrescriptionId class
    private PrescriptionId id; //collects the P.K of both doctor and patient and stores inside it

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
    private String prescription;

    @MapsId("doctorId") //Acts as an bridge, tells JPA that PrescriptionId entities doctorId field gets it P.K from Doctor class
    @ManyToOne(fetch= FetchType.LAZY, optional= false)
    @JoinColumn(name= "doctor_id")
    private Doctor doctorId;

    @MapsId("patientId") //tells JPA that PrescriptionId entities patientId field gets it P.K from Patient class
    @ManyToOne(fetch= FetchType.LAZY, optional= false)
    @JoinColumn(name= "patient_id")
    private Patient patientId;

    public Prescription(String prescription){
        this.prescription= prescription;
    }

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch= FetchType.LAZY)
    @JoinColumn(name= "patient")
    private Patient patient;

    @ManyToMany
    @JoinTable(
            name= "prescription_medicine",
            joinColumns= {
                    //Combining both the keys as one
                    @JoinColumn(name= "prescription_doctor_id", referencedColumnName = "doctor_id"),
                    @JoinColumn(name= "presrciption_patient_id", referencedColumnName= "patient_id")
            },
            inverseJoinColumns= @JoinColumn(name= "medicine_id")
    )
    private List<Medicine> medicines= new ArrayList<>();


}
