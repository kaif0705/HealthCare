package com.kaif.healthcare.Repositories;

import com.kaif.healthcare.ManyToMany.Prescription;
import com.kaif.healthcare.Model.PrescriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, PrescriptionId> {
    @Query("SELECT p FROM Prescription p WHERE p.patientId.id= ?1")
    List<Prescription> findPrescriptionByPatientId(Long patientId);

//    @Query("SELECT p FROM Prescription p WHERE p.patientId.id= ?1")
//    List<Prescription> findMedicineByMedicineId(Long id);
}
