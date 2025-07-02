package com.kaif.healthcare.Service;

import com.kaif.healthcare.Model.PrescriptionId;
import com.kaif.healthcare.ManyToMany.PrescriptionDetailsDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PrescriptionService {
    PrescriptionDetailsDTO createPrescription(PrescriptionDetailsDTO prescription);
    List<PrescriptionDetailsDTO> getAllPrescriptions();
    PrescriptionDetailsDTO getPrescriptionById(PrescriptionId prescriptionId);
    String deletePrescriptionById(@Valid PrescriptionId prescriptionId);
    PrescriptionDetailsDTO updatePrescription(PrescriptionDetailsDTO prescriptionDetailsDTO);
}
