package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.MedicalRecordRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FetchTypeDemo implements CommandLineRunner {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    public void run(String... args) throws Exception{

        MedicalRecord aliceMedicalRecord= new MedicalRecord("Fever");
        Doctor aliceDoctor= new Doctor("Smith");
        Patient alice= new Patient("Alice", 20);

        alice.setMedicalRecord(aliceMedicalRecord);
        alice.setDoctor(aliceDoctor);

        patientRepo.save(alice);

        Patient aliceFromDB= patientRepo.findById(alice.getId()).
                orElseThrow( () -> new Exception("Patient not found"));


    }

}
