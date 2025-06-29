package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import com.kaif.healthcare.Repositories.PersonRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private DoctorRepo doctorRepo;
//
//    @Autowired
//    private PatientRepo patientRepo;
//
//    @Autowired
//    private PersonRepo personRepo;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        /* Setting Doctor */
//        Doctor d1= new Doctor();
//        d1.setName("Dr.Smith");
//        d1.setAge(34);
//        d1.setGender(Gender.MALE);
//        d1.setEmail("smith@gmail.com");
//        d1.setSpeciality("Cardiology");
//
//        //Setting doctor address
//        Address smithAddress = new Address();
//        smithAddress.setStreet("Street");
//        smithAddress.setCity("City");
//        smithAddress.setState("State");
//        smithAddress.setZipcode("Zipcode");
//
//        d1.setDoctorAddress(smithAddress);
//
//
//        /* Setting Patient */
//        Patient p1= new Patient();
//        p1.setName("Alice");
//        p1.setAge(20);
//        p1.setGender(Gender.MALE);
//        p1.setEmail("alice@gmail.com");
//
//
//        Patient p2= new Patient();
//        p2.setName("Bob");
//        p2.setAge(22);
//        p2.setGender(Gender.MALE);
//        p2.setEmail("bob@gmail.com");
//
//
//        /* Setting Medical Record */
//        MedicalRecord mr1 = new MedicalRecord();
//        mr1.setPatient(p1);
//        mr1.setDiagnose("Hypertension");
//
//        MedicalRecord mr2 = new MedicalRecord();
//        mr2.setPatient(p2);
//        mr2.setDiagnose("Cancer");
//
//
//        /* Setting Bi-Directional Mapping */
//        p1.setDoctor(d1);
//        p1.setMedicalRecord(mr1);
//
//        p2.setDoctor(d1);
//        p2.setMedicalRecord(mr2);
//
//        patientRepo.save(p1);
//        patientRepo.save(p2);
//
//        TypedUntypedQuery so= new TypedUntypedQuery();
//        so.execute(em);
//
//    }
//}
