package com.kaif.healthcare;

import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Repositories.PatientRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private PatientRepo patientRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception{
        Patient p1= new Patient("AliceRepo", 30);
        patientRepo.save(p1);
        em.persist(p1);
        em.detach(p1);
//        System.out.println("Is Managed: "  + em.contains(p1));
//
//        Patient p1Id= em.find(Patient.class, 1L);
//        System.out.println("Id of p1 is: " + p1Id.getId());
//
//        em.getReference(Patient.class, 1L);

        Patient patientProxy= em.getReference(Patient.class, 1L);
        System.out.println(patientProxy);


    }
}
