package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Model.*;
import com.kaif.healthcare.Repositories.*;
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

    @Autowired
    private MedicineRepo mr;

    @Autowired
    private PrescriptionRepo prescriptionRepe;

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
        //for MedicalRecord entity
        p1MedicalRecord.setPatient(p1);

        //Saving, first save non-owning side in the DB
//        mrr.save(p1MedicalRecord);
//        dr.save(d1);

        //Finding medical record of the patient
//        MedicalRecord aliceFromRepo= mrr.findById(p1MedicalRecord.getId()).
//                orElseThrow(() -> new Exception("P1 Not Found"));
//        System.out.println(aliceFromRepo.getPatient().getName());

        //Fetching patient from DB
//        Patient pFromDB= pr.findById(p1.getId()).
//                orElseThrow(() -> new Exception("P1 Not Found"));
//        System.out.println(pFromDB.getDoctor().getDoctorName());

//        System.out.println(d1.getPatients().get(0).getName());

        //Remove
//        pr.deleteById(p1.getId());


        //Medicine and Prescription
        Medicine paracetamol= new Medicine("Paracetamol");
        Prescription p1Prescription= new Prescription("Prescription 1");

        //Bi-directional mapping
        //for Medicine entity
        paracetamol.getPrescription().add(p1Prescription);
        //for Prescription entity
        p1Prescription.getMedicine().add(paracetamol);

        //Save
        mr.save(paracetamol);

        //Get
//        Medicine medicineFromRepo= mr.findById(paracetamol.getId()).
//                orElseThrow(() -> new Exception("Medicine Not Found"));
//        System.out.println(medicineFromRepo.getPrescription().get(0).getPrescription());
//
//        Prescription prescriptionFromRepo= prescriptionRepe.findById(p1Prescription.getId()).
//                orElseThrow(() -> new Exception("Medicine Not Found"));
//        System.out.println(prescriptionFromRepo.getMedicine().get(0).getMedicine());

        //@Embeddable and @Embedded
        //Instantiating address for patient
        Address patientAddress= p1.getPatientAddress();
        patientAddress.setStreet("Street 1");
        patientAddress.setCity("City 1");
        patientAddress.setState("State 1");
        patientAddress.setZipcode("Zipcode 1");

        //Instantiating address for doctor
        Address doctorAddress= d1.getDoctorAddress();
        doctorAddress.setStreet("Street 2");
        doctorAddress.setCity("City 2");
        doctorAddress.setState("State 2");
        doctorAddress.setZipcode("Zipcode 2");

        //Saving Address
        pr.save(p1);

    }

}
