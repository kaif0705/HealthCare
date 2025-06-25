package com.kaif.healthcare.Repositories;

import com.kaif.healthcare.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
}
