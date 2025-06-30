package com.kaif.healthcare.Service;

import com.kaif.healthcare.Payloads.MedicineDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface MedicineService {
    MedicineDTO addMedicine(MedicineDTO medicine);
    List<MedicineDTO> getAllMedicines();
}
