package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import com.kaif.healthcare.Repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InheritanceDemo implements CommandLineRunner {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void run(String... args) throws Exception {

        /* Setting Doctor */
        Doctor d1= new Doctor();
        d1.setName("Dr.Smith");
        d1.setAge(34);
        d1.setGender(Gender.MALE);
        d1.setEmail("smith@gmail.com");
        d1.setSpeciality("Cardiology");

        //Setting doctor address
        Address smithAddress = new Address();
        smithAddress.setStreet("Street");
        smithAddress.setCity("City");
        smithAddress.setState("State");
        smithAddress.setZipcode("Zipcode");

        d1.setDoctorAddress(smithAddress);

        /* Setting Patient */
        Patient p1= new Patient();

        p1.setName("Alice");
        p1.setAge(20);
        p1.setGender(Gender.MALE);
        p1.setEmail("alice@gmail.com");

        p1.setDoctor(d1);
        patientRepo.save(p1);
        doctorRepo.save(d1);

    }
}
