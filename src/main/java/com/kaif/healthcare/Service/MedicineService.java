package com.kaif.healthcare.Service;

import com.kaif.healthcare.ManyToMany.MedicineDetailsDTO;

import java.util.List;

public interface MedicineService {
    MedicineDetailsDTO addMedicine(MedicineDetailsDTO medicine);
    List<MedicineDetailsDTO> getAllMedicines();
    MedicineDetailsDTO getMedicine(Long medicineId);
    String deleteMedicine(Long medicineId);
    MedicineDetailsDTO updateMedicine(MedicineDetailsDTO medicineDetailsDTO);
}
