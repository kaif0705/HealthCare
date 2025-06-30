package com.kaif.healthcare.Service;

import com.kaif.healthcare.Payloads.MedicineDTO;
import jakarta.validation.Valid;

public interface MedicineService {
    MedicineDTO addMedicine(MedicineDTO medicine);
}
