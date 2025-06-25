package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Repositories.MedicalRecordRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaRelationshipDemo implements CommandLineRunner {

    @Autowired
    private PatientRepo pr;

    @Autowired
    private MedicalRecordRepo mrr;

    @Override
    public void run(String... args) throws Exception{

        MedicalRecord aliceMedicalRecord= new MedicalRecord("Fever");
        mrr.save(aliceMedicalRecord);

        Patient p1= new Patient("Alice", 20);
        p1.setMedicalRecord(aliceMedicalRecord);
        pr.save(p1);

        MedicalRecord aliceFromRepo= mrr.findById(aliceMedicalRecord.getId()).
                orElseThrow(() -> new Exception("Alice Not Found"));

        System.out.println(aliceFromRepo.getPatient().getName());

    }

}
