package com.kaif.healthcare.Repositories;

import com.kaif.healthcare.ManyToMany.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {

    @Query("SELECT m FROM Medicine m WHERE m.medicine= ?1")
    String findByName(String medicine);
}
