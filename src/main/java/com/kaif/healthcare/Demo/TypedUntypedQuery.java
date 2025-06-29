package com.kaif.healthcare.Demo;

import com.kaif.healthcare.Model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//public class TypedUntypedQuery{
//
//    public void execute(EntityManager em){
//
//        TypedQuery<Patient> typedQuery = em.createQuery(
//                "SELECT p FROM Patient p WHERE p.id = ?1",
//                Patient.class
//        );
//        typedQuery.setParameter(1, 2);
//        List<Patient> patients= typedQuery.getResultList();
//        for(Patient obj : patients){
//            System.out.println(obj.getName());
//        }
//
//    }
//}
