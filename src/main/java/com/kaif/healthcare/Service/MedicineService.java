package com.kaif.healthcare.Service;

import com.kaif.healthcare.ManyToMany.MedicineListDTO;

import java.util.List;

public interface MedicineService {
    MedicineListDTO addMedicine(MedicineListDTO medicine);
    List<MedicineListDTO> getAllMedicines();
    MedicineListDTO getMedicine(Long medicineId);
    String deleteMedicine(Long medicineId);
    MedicineListDTO updateMedicine(MedicineListDTO medicineListDTO);
}
