package com.kaif.healthcare.Repositories;

import com.kaif.healthcare.Model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepo extends JpaRepository<MedicalRecord, Long> {
}
