package com.kaif.healthcare.Repositories;

import com.kaif.healthcare.Model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {
}
