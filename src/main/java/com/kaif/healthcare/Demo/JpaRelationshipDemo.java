package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.MedicalRecordRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaRelationshipDemo implements CommandLineRunner {

    @Autowired
    private PatientRepo pr;

    @Autowired
    private MedicalRecordRepo mrr;

    @Autowired
    private DoctorRepo dr;

    @Override
    @Transactional
    public void run(String... args) throws Exception{

        //Creating Patient, Doctor and Medical Record instance
        Patient p1= new Patient("P1", 20);
        MedicalRecord p1MedicalRecord= new MedicalRecord("Fever");
        Doctor d1= new Doctor("Doctor1");

        //Bi-directional mapping
        //for patient entity
        p1.setMedicalRecord(p1MedicalRecord);
        p1.setDoctor(d1);
        //for doctor entity
        d1.getPatients().add(p1);

        //Saving, first save non-owning side in the DB
        mrr.save(p1MedicalRecord);
        dr.save(d1);
        pr.save(p1);

        //Finding medical record of the patient
        MedicalRecord aliceFromRepo= mrr.findById(p1MedicalRecord.getId()).
                orElseThrow(() -> new Exception("P1 Not Found"));
        System.out.println(aliceFromRepo.getPatient().getName());

        //Fetching patient from DB
        Patient pFromDB= pr.findById(p1.getId()).
                orElseThrow(() -> new Exception("P1 Not Found"));
        System.out.println(pFromDB.getDoctor().getDoctorName());

        System.out.println(d1.getPatients().get(0).getName());

    }

}
