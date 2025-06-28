package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.*;
import com.kaif.healthcare.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
//public class CompositeKeyDemo implements CommandLineRunner {

//    @Autowired
//    private PatientRepo patientRepo;
//
//    @Autowired
//    private DoctorRepo doctorRepo;
//
//    @Autowired
//    private MedicalRecordRepo medicalRecordRepo;
//
//    @Autowired
//    private MedicineRepo medicineRepo;
//
//    @Autowired
//    private PrescriptionRepo prescriptionRepo;

//    @Transactional
//    public void run(String... args) throws Exception{
//
//        //Creating instances for patient, doctor and Medicine
//        Doctor doctorSmith= new Doctor("Smith");
//        MedicalRecord medicalRecordSmith= new MedicalRecord("Fever");
//        Patient patientAlice = new Patient("Alice", 20);
//
//        //Creating Medicine instances
//        Medicine m1= new Medicine("Medicine 1");
//        Medicine m2= new Medicine("Medicine 2");
//        Medicine m3= new Medicine("Medicine 3");
//
//        List<Medicine> medicineList = new ArrayList<>();
//        medicineList.add(m1);
//        medicineList.add(m2);
//        medicineList.add(m3);
//
//        //Setting data of Patient
//        patientAlice.setGender(Gender.MALE);
//        patientAlice.setDoctor(doctorSmith);
//        patientAlice.setMedicalRecord(medicalRecordSmith);
//
//        //Saving patient, doctor and Medicine
//        medicineRepo.saveAll(medicineList);
//        doctorRepo.save(doctorSmith);
////        patientRepo.save(patientAlice);
//
//        //Creating instance of Prescription
//        Prescription prescriptionSmith= new Prescription();
//
//        //Explicitly saving Doctor and Patient instance in Prescription instance
//        prescriptionSmith.setDoctorId(doctorSmith);
//        prescriptionSmith.setPatientId(patientAlice);
//
//        // CRUCIAL: Manually set the @EmbeddedId field using the IDs of the now-persisted Doctor and Patient
//        prescriptionSmith.setId(new PrescriptionId(doctorSmith.getId(), patientAlice.getId()));
//
//        //Setting medicine in prescription
//        for(Medicine obj : medicineList){
//            prescriptionSmith.getMedicines().add(obj);
//        }
//
//        //Setting Patient and prescription
//        prescriptionSmith.setPatient(patientAlice);
//        prescriptionSmith.setPrescription("Alice has fever");
//
////        prescriptionSmith.getMedicines().addAll(medicineList);
//        prescriptionRepo.save(prescriptionSmith);
//
//        //Setting prescription on patient
//        patientAlice.getPrescription().add(prescriptionSmith);
//        patientRepo.save(patientAlice);
//
//
//
//    }
//
//}
