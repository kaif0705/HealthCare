package com.kaif.healthcare.Service;

import com.kaif.healthcare.Payloads.MedicineDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface MedicineService {
    MedicineDTO addMedicine(MedicineDTO medicine);
    List<MedicineDTO> getAllMedicines();
    MedicineDTO getMedicine(Long medicineId);
    String deleteMedicine(Long medicineId);
    MedicineDTO updateMedicine(MedicineDTO medicineDTO);
}
